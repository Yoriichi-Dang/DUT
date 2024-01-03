package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.TeacherModel;
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

@WebServlet(urlPatterns = {"/admin-teacher-page"})

public class TeacherController extends HttpServlet {
    @Inject
    private IFacultyService facultyService;
    @Inject
    private ITeacherService teacherService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherModel teacherModel= FormUtil.toModel(TeacherModel.class,request);
        String views="";
        if(teacherModel.getType()==null||teacherModel.getType().equals(SystemConstant.LIST)){
            teacherModel.setList(teacherService.findAll());
            views="/views/admin/Teacher/list.jsp";
            request.setAttribute("faculties",facultyService.findAll());
        }
        else if(teacherModel.getType().equals(SystemConstant.EDIT)){
            //bổ sung DAO tìm kiếm vì lỗi
            if(teacherModel.getId()!=null){
                teacherModel=teacherService.findById(teacherModel.getId());
                views="/views/admin/Teacher/edit.jsp";
                request.setAttribute("faculties",facultyService.findAll());
            }
        }else if(teacherModel.getType().equals(SystemConstant.DELETE)){
            if(teacherModel.getId()!=null){
                teacherService.delete(teacherModel.getId());
                teacherModel.setList(teacherService.findAll());
                views="/views/admin/Teacher/list.jsp";
            }
        }
        else if(teacherModel.getType().equals(SystemConstant.CHANGEPASSWORD)){
            if(teacherModel.getId()!=null){
                teacherModel=teacherService.findById(teacherModel.getId());
                views="/views/admin/Teacher/changePassword.jsp";
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,teacherModel);
        rd.forward(request,response);
    }
}
