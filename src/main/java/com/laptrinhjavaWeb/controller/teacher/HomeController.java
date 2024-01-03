package com.laptrinhjavaWeb.controller.teacher;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.TeacherModel;
import com.laptrinhjavaWeb.service.IStudentService;
import com.laptrinhjavaWeb.service.ITeacherService;
import com.laptrinhjavaWeb.service.IUserService;
import com.laptrinhjavaWeb.util.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/teacher-home-page"})

public class HomeController extends HttpServlet {
    @Inject
    private IUserService userService;

    @Inject
    private ITeacherService teacherService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherModel teacherModel= FormUtil.toModel(TeacherModel.class,request);
        String views="/views/teacher/profile.jsp";
//        views="/views/student/changePassword.jsp";
        if(teacherModel.getType()==null){
            teacherModel=teacherService.findByCode(teacherModel.getCode());
        }else if(teacherModel.getType().equals(SystemConstant.CHANGEPASSWORD)){
            views="/views/teacher/changePassword.jsp";
            teacherModel=teacherService.findById(teacherModel.getId());
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,teacherModel);
        rd.forward(request,response);
    }
}
