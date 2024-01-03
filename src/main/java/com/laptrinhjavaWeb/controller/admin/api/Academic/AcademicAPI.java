package com.laptrinhjavaWeb.controller.admin.api.Academic;

import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IAcademicService;
import com.laptrinhjavaWeb.util.HttpUtil;
import com.laptrinhjavaWeb.util.SessionUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api-admin-academic"})

public class AcademicAPI extends HttpServlet {
    @Inject
    private IAcademicService academicService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        AcademicModel academicModel= HttpUtil.of(request.getReader()).toModel(AcademicModel.class);
        System.out.println(academicModel.getCode());
        System.out.println(academicModel.getName());
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        academicModel.setCreateBy(userModel.getUsername());
        Long id=academicService.insert(academicModel);
        List<AcademicModel> academicModelList=null;
        if(id!=-1){
            academicModelList=academicService.findAll();
        }
        mapper.writeValue(response.getOutputStream(),academicModelList);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        AcademicModel academicModel= HttpUtil.of(request.getReader()).toModel(AcademicModel.class);
        UserModel userModel=(UserModel)SessionUtil.getInstance().getValue(request,"USERMODEL");
        academicModel.setModifiedBy(userModel.getUsername());
        if(academicModel.getId()!=null){
            academicModel=academicService.update(academicModel);
        }
        mapper.writeValue(response.getOutputStream(),academicModel);//change java object to json if write and read json to java object

    }
}
