package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.service.IAcademicService;
import com.laptrinhjavaWeb.service.IClassService;
import com.laptrinhjavaWeb.util.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-class-page"})

public class ClassController extends HttpServlet {
    @Inject
    private IAcademicService academicService;
    @Inject
    private IClassService classService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassModel classModel= FormUtil.toModel(ClassModel.class,request);
        String views="";
        if(classModel.getType()==null||classModel.getType().equals(SystemConstant.LIST)){
            classModel.setList(classService.findAll());
            views="/views/admin/Class/list.jsp";
            request.setAttribute("classes",classService.findAll());
            request.setAttribute("academics",academicService.findAll());
        }
        else if(classModel.getType().equals(SystemConstant.EDIT)){
            //bổ sung DAO tìm kiếm vì lỗi
            if(classModel.getId()!=null){
                classModel=classService.findClasById(classModel.getId());
                request.setAttribute("classes",classService.findAll());
            }
            views="/views/admin/Class/edit.jsp";
        }else if(classModel.getType().equals(SystemConstant.DELETE)){
            if(classModel.getId()!=null){
                classService.delete(classModel.getId());
                classModel.setList(classService.findAll());
                views="/views/admin/Class/list.jsp";
            }
        }
        else if(classModel.getType().equals(SystemConstant.LISTSTUDENT)){
            if(classModel.getId()!=null){
                classModel=classService.findClasById(classModel.getId());
                classModel=classService.findAllStudentByClassCode(classModel.getCode());
                views="/views/admin/Class/listStudent.jsp";
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,classModel);
        rd.forward(request,response);
    }
}
