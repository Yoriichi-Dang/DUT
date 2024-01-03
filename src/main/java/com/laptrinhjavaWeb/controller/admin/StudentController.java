package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.service.IClassService;
import com.laptrinhjavaWeb.service.IEducationService;
import com.laptrinhjavaWeb.service.IStudentService;
import com.laptrinhjavaWeb.util.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-student-page"})
public class StudentController extends HttpServlet {
    @Inject
    private IClassService classService;
    @Inject
    private IStudentService studentService;
    @Inject
    private IEducationService educationService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentModel studentModel= FormUtil.toModel(StudentModel.class,request);
        String views="";
        if(studentModel.getType()==null||studentModel.getType().equals(SystemConstant.LIST)){
            studentModel.setList(studentService.findAll());
            views="/views/admin/Student/list.jsp";
            request.setAttribute("classes",classService.findAll());
            request.setAttribute("students",studentService.findAll());
        }
        else if(studentModel.getType().equals(SystemConstant.EDIT)){
            //bổ sung DAO tìm kiếm vì lỗi
            if(studentModel.getId()!=null){
                studentModel=studentService.findStudentById(studentModel.getId());
                request.setAttribute("educations",educationService.findAllEducationByStudentCode(studentModel.getCode()));
                request.setAttribute("classes",classService.findAll());
            }
            views="/views/admin/Student/edit.jsp";
        }else if(studentModel.getType().equals(SystemConstant.DELETE)){
            if(studentModel.getId()!=null){
                studentService.delete(studentModel.getId());
                studentModel.setList(studentService.findAll());
                views="/views/admin/Student/list.jsp";
            }
        }
        else if(studentModel.getType().equals(SystemConstant.CHANGEPASSWORD)){
            if(studentModel.getId()!=null){
                studentModel=studentService.findStudentById(studentModel.getId());
                views="/views/admin/Student/changePassword.jsp";
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,studentModel);
        rd.forward(request,response);
    }
}
