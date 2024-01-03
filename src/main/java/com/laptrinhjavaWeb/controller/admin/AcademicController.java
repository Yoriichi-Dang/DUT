package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.service.IAcademicService;
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

//ngành
@WebServlet(urlPatterns = {"/admin-academic-page"})
public class AcademicController extends HttpServlet {
    @Inject
    private IAcademicService academicService;
    @Inject
    private IFacultyService facultyService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcademicModel academicModel= FormUtil.toModel(AcademicModel.class,request);
        String views="";
        if(academicModel.getType()==null||academicModel.getType().equals(SystemConstant.LIST)){
            academicModel.setList(academicService.findAll());
            views="/views/admin/Academic/list.jsp";
            request.setAttribute("faculties",facultyService.findAll());
        }
        else if(academicModel.getType().equals(SystemConstant.EDIT)){
            if(academicModel.getId()!=null){
                academicModel=academicService.findById(academicModel.getId());
                request.setAttribute("academic",academicModel);
                views="/views/admin/Academic/edit.jsp";
            }
        }else if(academicModel.getType().equals(SystemConstant.DELETE)){
            if(academicModel.getId()!=null){
                academicService.delete(academicModel.getId());
                academicModel.setList(academicService.findAll());
                views="/views/admin/Academic/list.jsp";
            }
        }else if(academicModel.getType().equals(SystemConstant.LISTCLASS)){
            if(academicModel.getId()!=null){
                academicModel=academicService.findById(academicModel.getId());
                academicModel=academicService.findAllClassByCode(academicModel.getCode());
                views="/views/admin/Academic/listClass.jsp";
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,academicModel);
        rd.forward(request,response);
    }
}
