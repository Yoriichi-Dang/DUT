package com.laptrinhjavaWeb.controller.student.RegisterCourse;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.dao.IClassCourseDAO;
import com.laptrinhjavaWeb.model.*;
import com.laptrinhjavaWeb.service.*;
import com.laptrinhjavaWeb.util.FormUtil;
import com.laptrinhjavaWeb.util.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/student-register-course-page"})

public class RegisterCourseController extends HttpServlet {
    @Inject
    private IStudentService studentService;
    @Inject
    private IRegisterCourseService registerCourseService;
    @Inject
    private ICourseDependentService courseDependentService;
    @Inject
    private IClassCourseStudentService classCourseStudentService;
    @Inject
    private IClassCourseService classCourseService;
    @Inject
    private ICourseService courseService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get date time gần nhất
        String views="/views/student/RegisterCourse/cannotRegisterCourse.jsp";
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        TimeRegisterCourseModel timeRegisterCourseModel=registerCourseService.findByStartTime(timestamp);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        StudentModel studentModel=studentService.findStudentByCode(userModel.getUsername());
        if(timeRegisterCourseModel==null){// chưa đến giờ đăng ký
            request.setAttribute("message","Chưa đến giờ đăng ký học phần");
        }else{// đến giờ thì kiểm tra sinh viên đó nằm trong loại đăng ký nào
            if(studentService.findStudentInFaculty(timeRegisterCourseModel.getValue(),userModel.getUsername())==null){
              //không thuộc
                request.setAttribute("message","Chưa đến đăng ký theo khoa của bạn");
            }else{
                views="/views/student/RegisterCourse/registerCourse.jsp";
                ClassCourseModel classCourseModel= FormUtil.toModel(ClassCourseModel.class,request);
                List<ClassCourseModel>classCourseModels=filterRegisterCourse(userModel);// danh sách các học phần được đky
                //lọc các học phần đã có trong lịch sử đăng ký không cần hiển thị nữa
                ClassCourseModel historyRegisterClassCourse=classCourseService.findAllRegisterClassCourseByStudentCode(userModel.getUsername());
                for(int i=0;i<classCourseModels.size();i++){
                    for(int j=0;j<historyRegisterClassCourse.getList().size();j++){
                        if(classCourseModels.get(i).getCourseModel().getCode().equals(historyRegisterClassCourse.getList().get(j).getCourseModel().getCode())){
                            classCourseModels.remove(i);
                            i--;
                            break;
                        }
                    }
                }
                request.setAttribute("historyRegisterClassCourse",historyRegisterClassCourse);
                for(int i=0;i<classCourseModels.size();i++){
                    classCourseModels.get(i).setList(classCourseService.findAllByCourseCode(classCourseModels.get(i).getCourseModel().getCode()).getList());
                }
                if(classCourseModel.getType()!=null&&classCourseModel.getType().equals(SystemConstant.CLASSCOURSE)){
                    if(classCourseModel.getCourseCode()!=null){
                        classCourseModel.setCourseModel(courseService.findByCode(classCourseModel.getCourseCode()));
                        List<ClassCourseModel>classCourseModelList=classCourseService.findAllByCourseCode(classCourseModel.getCourseCode()).getList();
                        for(int i=0;i<classCourseModelList.size();i++){
                            classCourseModelList.get(i).setList(classCourseService.findAllStudentInClassCouse(classCourseModelList.get(i).getCode()).getList());
                        }
                        classCourseModel.setList(classCourseModelList);
                        request.setAttribute("classCourseDetail",classCourseModel);// lấy chi tiết lớp học phần khi chọn học phần đăng ký

                    }
                }

                request.setAttribute("ClassCourseRegister",classCourseModels);
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,studentModel);
        rd.forward(request,response);
    }
    public List<ClassCourseModel>filterRegisterCourse(UserModel userModel){
        List<ClassCourseModel>classCourseModels=new ArrayList<>();
        CourseDependentModel courseDependentModel=courseDependentService.findAllCourseInEducationByStudentCode(userModel.getUsername());
        List<CourseDependentModel>list=courseDependentModel.getList();
        boolean check=true;
        for(int i=0;i<list.size();i++){
            check=true;
            CourseDependentModel model=list.get(i);
            ClassCourseStudentModel classCourseStudentModel=new ClassCourseStudentModel();

            if(model.getDependentList_courseStudyBeforeCode().size()>0||model.getDependentList_courseStudyBeforeCode()!=null){
                for(int j=0;j<model.getDependentList_courseStudyBeforeCode().size();j++){
                    classCourseStudentModel=classCourseStudentService.findResultStudy(model.getDependentList_courseStudyBeforeCode().get(j).getCode(),userModel.getUsername());
                    if(classCourseStudentModel==null){
                        check=false;
                        break;
                    }
                }
            }
            if(model.getDependentList_courseStudyTogetherCode().size()>0||model.getDependentList_courseStudyTogetherCode()!=null){
                // Nếu có học phần song hành thì kiểm tra trong lịch sử đăng ký của sinh viên đã đăng ký hc  phần song hành đó chưa , có thì thêm vào và ngược lại
                for(int j=0;j<model.getDependentList_courseStudyTogetherCode().size();j++){// danh sachs hoc phan song hanh
                    classCourseStudentModel=classCourseStudentService.findHistoryRegisterByCourseCode(model.getDependentList_courseStudyTogetherCode().get(j).getCode());
                    if(classCourseStudentModel==null){
                        check=false;
                        break;
                    }
                }
            }
            //nếu cả hai đều null thì kiểm tra đã học chưa nếu chưa học thì bỏ vào
            if(model.getDependentList_courseStudyTogetherCode().isEmpty()&&model.getDependentList_courseStudyBeforeCode().isEmpty()){
                ClassCourseModel classCourseModel=new ClassCourseModel();
                classCourseModel.setCourseCode(model.getCourseModel().getCode());
                classCourseModel.setStudentCode(userModel.getUsername());
                if(classCourseService.findClassCourseStudied(classCourseModel)!=null){
                    check=false;
                }
            }
            if(check){
                ClassCourseModel classCourseModel=new ClassCourseModel();
                classCourseModel.setCourseModel(model.getCourseModel());
                classCourseModels.add(classCourseModel);
            }
        }
        return classCourseModels;
    }
}
