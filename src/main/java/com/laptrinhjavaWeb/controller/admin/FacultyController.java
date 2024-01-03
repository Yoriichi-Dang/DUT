package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.service.IFacultyService;
import com.laptrinhjavaWeb.util.FormUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-faculty-page"})

public class FacultyController extends HttpServlet {
    @Inject
    private IFacultyService facultyService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FacultyModel facultyModel= FormUtil.toModel(FacultyModel.class,request);
        String views="";
        if(facultyModel.getType()==null||facultyModel.getType().equals(SystemConstant.LIST)){
            facultyModel.setList(facultyService.findAll());
            views="/views/admin/Faculty/list.jsp";
        }
        else if(facultyModel.getType().equals(SystemConstant.EDIT)){
            if(facultyModel.getId()!=null){
                facultyModel=facultyService.findById(facultyModel.getId());
            }
            views="/views/admin/Faculty/edit.jsp";
        }else if(facultyModel.getType().equals(SystemConstant.DELETE)){
            if(facultyModel.getId()!=null){
                facultyService.delete(facultyModel.getId());
                facultyModel.setList(facultyService.findAll());
                views="/views/admin/Faculty/list.jsp";
            }
        }else if(facultyModel.getType().equals(SystemConstant.LISTACADEMIC)){
            if(facultyModel.getId()!=null){
                facultyModel=facultyService.findById(facultyModel.getId());
                facultyModel=facultyService.findAllAcademicByCode(facultyModel.getCode());
                views="/views/admin/Faculty/listAcademic.jsp";
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,facultyModel);
        rd.forward(request,response);
    }
}
