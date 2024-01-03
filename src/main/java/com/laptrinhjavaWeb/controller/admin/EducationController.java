package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.EducationModel;
import com.laptrinhjavaWeb.service.IAcademicService;
import com.laptrinhjavaWeb.service.ICourseService;
import com.laptrinhjavaWeb.service.IEducationService;
import com.laptrinhjavaWeb.service.IFacultyService;
import com.laptrinhjavaWeb.util.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-education-page"})
public class EducationController extends HttpServlet {
    @Inject
    private IEducationService educationService;
    @Inject
    private IFacultyService facultyService;
    @Inject
    private IAcademicService academicService;
    @Inject
    private ICourseService courseService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EducationModel educationModel= FormUtil.toModel(EducationModel.class,request);
        request.setAttribute("faculties",facultyService.findAll());
        String views="";
        if(educationModel.getType()==null||educationModel.getType().equals(SystemConstant.LIST)){
            educationModel.setList(educationService.findAll());
            views="/views/admin/Education/list.jsp";
            request.setAttribute("academics",academicService.findAll());
            educationModel.setList(educationService.findAll());
        }
        else if(educationModel.getType().equals(SystemConstant.EDIT)){
            //bổ sung DAO tìm kiếm vì lỗi
            if(educationModel.getId()!=null){
                educationModel=educationService.findById(educationModel.getId());
                request.setAttribute("academics",academicService.findAll());
                views="/views/admin/Education/edit.jsp";
            }
        }else if(educationModel.getType().equals(SystemConstant.DELETE)){
            if(educationModel.getId()!=null){
                educationService.delete(educationModel.getId());
                educationModel.setList(educationService.findAll());
                views="/views/admin/Education/list.jsp";
            }
        }
        else if(educationModel.getType().equals(SystemConstant.LISTCOURSE)){
            if(educationModel.getId()!=null){
                educationModel= educationService.findById(educationModel.getId());
                views="/views/admin/Education/addListCourse.jsp";
                educationModel=educationService.findAllCourseByCode(educationModel.getCode());
                CourseModel courseModel=new CourseModel();
                courseModel.setList(courseService.findAll());
                for(int i=0;i<educationModel.getCourseModelList().size();i++){
                    for(int j=0;j<courseModel.getList().size();j++){
                        if(educationModel.getCourseModelList().get(i).getCode().equals(courseModel.getList().get(j).getCode())){
                            courseModel.getList().remove(j);
                            break;
                        }
                    }
                }
                educationModel.setCourseModelList(courseModel.getList());
            }
        }else if(educationModel.getType().equals(SystemConstant.EDUCATIONCOURSE)){
            if(educationModel.getCode()!=null){
                educationModel=educationService.findAllCourseByCode(educationModel.getCode());
                System.out.println(educationModel.getCode());
                views="/views/admin/Education/listCourse.jsp";
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,educationModel);
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
