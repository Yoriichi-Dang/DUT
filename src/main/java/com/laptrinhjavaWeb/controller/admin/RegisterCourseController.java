package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.TimeRegisterCourseModel;
import com.laptrinhjavaWeb.service.IFacultyService;
import com.laptrinhjavaWeb.service.IRegisterCourseService;
import com.laptrinhjavaWeb.util.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-register-course"})

public class RegisterCourseController extends HttpServlet {
    @Inject
    private IRegisterCourseService registerCourseService;
    @Inject
    private IFacultyService facultyService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TimeRegisterCourseModel timeRegisterCourseModel= FormUtil.toModel(TimeRegisterCourseModel.class,request);
        String views="/views/admin/RegisterCourse/setting.jsp";
        if(timeRegisterCourseModel.getType()==null||timeRegisterCourseModel.getType().equals(SystemConstant.LIST)){
            timeRegisterCourseModel=registerCourseService.findAll();
            request.setAttribute("faculties",facultyService.findAll());
        }else if(timeRegisterCourseModel.getType().equals(SystemConstant.EDIT)){
            if(timeRegisterCourseModel.getId()!=null){

            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,timeRegisterCourseModel);
        rd.forward(request,response);
    }
}
