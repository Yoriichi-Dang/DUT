package com.laptrinhjavaWeb.controller.student;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IClassService;
import com.laptrinhjavaWeb.service.IStudentService;
import com.laptrinhjavaWeb.service.IUserService;
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

@WebServlet(urlPatterns = {"/student-home-page"})

public class HomeController extends HttpServlet {
    @Inject
    private IUserService userService;
    @Inject
    private IClassService classService;
    @Inject
    private IStudentService studentService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentModel studentModel= FormUtil.toModel(StudentModel.class,request);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        String views="/views/student/profile.jsp";
//        views="/views/student/changePassword.jsp";
        if(studentModel.getType()==null){
            studentModel=studentService.findStudentByCode(studentModel.getCode());
        }else if(studentModel.getType().equals(SystemConstant.CHANGEPASSWORD)){
            views="/views/student/changePassword.jsp";
            studentModel=studentService.findStudentById(studentModel.getId());
        }else if(studentModel.getType().equals(SystemConstant.LISTSTUDENT)){
            views="/views/student/studentClass.jsp";
            studentModel=studentService.findStudentByCode(userModel.getUsername());
            ClassModel classModel=new ClassModel();
            classModel=classService.findAllStudentByClassCode(studentModel.getClassCode());
            if(classModel!=null) studentModel.setList(classModel.getStudentModelList());
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,studentModel);
        rd.forward(request,response);
    }
}
