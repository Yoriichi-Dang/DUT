package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.service.*;
import com.laptrinhjavaWeb.util.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-class-course-page"})

public class ClassCourseController extends HttpServlet {
    @Inject
    private IClassCourseService classCourseService;
    @Inject
    private ICourseService courseService;
    @Inject
    private IFacultyService facultyService;
    @Inject
    private IRoomService roomService;
    @Inject
    private ITeacherService teacherService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassCourseModel classCourseModel= FormUtil.toModel(ClassCourseModel.class,request);
        String views="/views/admin/Course/ClassCourse/list.jsp";
        if(classCourseModel.getType()==null||classCourseModel.getType().equals(SystemConstant.CLASSCOURSE)){
            if(classCourseModel.getCourseCode()!=null){
                views="/views/admin/Course/ClassCourse/list.jsp";
                classCourseModel=classCourseService.findAllByCourseCode(classCourseModel.getCourseCode());

                classCourseModel.setCourseModel(courseService.findByCode(classCourseModel.getCourseCode()));
            }
        }else if(classCourseModel.getType().equals(SystemConstant.EDIT)){
            if(classCourseModel.getId()!=null){
                views="/views/admin/Course/ClassCourse/edit.jsp";
                classCourseModel=classCourseService.findById(classCourseModel.getId());
                request.setAttribute("rooms",roomService.findAll());
                request.setAttribute("teachers",teacherService.findAll());
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,classCourseModel);
        rd.forward(request,response);
    }
}
