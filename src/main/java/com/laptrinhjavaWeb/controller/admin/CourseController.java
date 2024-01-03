package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.service.ICourseService;
import com.laptrinhjavaWeb.service.IFacultyService;
import com.laptrinhjavaWeb.service.ITeacherService;
import com.laptrinhjavaWeb.util.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-course-page"})

public class CourseController extends HttpServlet {
    @Inject
    private IFacultyService facultyService;
    @Inject
    private ICourseService courseService;
    @Inject
    private ITeacherService teacherService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseModel courseModel= FormUtil.toModel(CourseModel.class,request);
        String views="";
        if(courseModel.getType()==null||courseModel.getType().equals(SystemConstant.LIST)){
            courseModel.setList(courseService.findAll());
            views="/views/admin/Course/list.jsp";
            request.setAttribute("faculties",facultyService.findAll());
        }
        else if(courseModel.getType().equals(SystemConstant.EDIT)){
            //bổ sung DAO tìm kiếm vì lỗi
            if(courseModel.getId()!=null){
                courseModel=courseService.findById(courseModel.getId());
                request.setAttribute("faculties",facultyService.findAll());
                request.setAttribute("classes",courseService.findAll());
                courseModel.setList(courseService.findAll());
                views="/views/admin/Course/edit.jsp";
            }
        }else if(courseModel.getType().equals(SystemConstant.DELETE)){
            if(courseModel.getId()!=null){
                courseService.delete(courseModel.getId());
                courseModel.setList(courseService.findAll());
                views="/views/admin/Course/list.jsp";
            }
        }else if(courseModel.getType().equals(SystemConstant.ADDCLASSCOURSE)){
            if(courseModel.getId()!=null){
                courseModel=courseService.findById(courseModel.getId());
                views="/views/admin/Course/ClassCourse/addClassCourse.jsp";
                request.setAttribute("teachers",teacherService.findAll());
            }
        }else if(courseModel.getType().equals(SystemConstant.DEPENDENTCOURSE)){
            views="/views/admin/Course/dependentList.jsp";
            List<CourseModel>courseModelList=courseService.findAll();
            System.out.println(courseModel.getCode());
            if(courseModel.getCode()!=null){
                for(int i=0;i<courseModelList.size();i++){
                    if(courseModelList.get(i).getCode().equals(courseModel.getCode())){
                        courseModelList.remove(i);
                        break;
                    }
                }
                courseModel=courseService.findAllCourseDependent(courseModel.getCode());
                for(int i=0;i<courseModel.getList().size();i++){
                    for(int j=0;j<courseModelList.size();j++){
                        if(courseModel.getList().get(i).getCode().equals(courseModelList.get(j).getCode())){
                            courseModelList.remove(j);
                            break;
                        }
                    }
                }
            }
            request.setAttribute("faculties",facultyService.findAll());
            request.setAttribute("courses",courseModelList);
        }else if(courseModel.getType().equals(SystemConstant.EDITDEPENDENTCOURSE)){
            if(courseModel.getId()!=null){
                courseModel=courseService.findDepentdentCourseById(courseModel.getId());
                views="/views/admin/Course/editDependentCourse.jsp";
            }
        }else if(courseModel.getType().equals(SystemConstant.DELETEDEPENDENTCOURSE)){
            if(courseModel.getId()!=null){
                courseService.deleteDepentdentCourse(courseModel.getId());
                courseModel.setList(courseService.findAll());
                views="/views/admin/Course/list.jsp";
                request.setAttribute("faculties",facultyService.findAll());
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,courseModel);
        rd.forward(request,response);
    }
}
