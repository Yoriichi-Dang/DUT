package com.laptrinhjavaWeb.controller.admin.api.Faculty;

import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IFacultyService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api-admin-faculty"})

public class FacultyAPI extends HttpServlet {
    @Inject
    private IFacultyService facultyService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        FacultyModel facultyModel= HttpUtil.of(request.getReader()).toModel(FacultyModel.class);
        System.out.println(facultyModel.getCode());
        System.out.println(facultyModel.getName());
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        facultyModel.setCreateBy(userModel.getUsername());
        Long id=facultyService.insert(facultyModel);
        List<FacultyModel>facultyModelList=null;
        if(id!=-1){
            facultyModelList=facultyService.findAll();
        }
        mapper.writeValue(response.getOutputStream(),facultyModelList);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        FacultyModel facultyModel= HttpUtil.of(request.getReader()).toModel(FacultyModel.class);
        UserModel userModel=(UserModel)SessionUtil.getInstance().getValue(request,"USERMODEL");
        facultyModel.setModifiedBy(userModel.getUsername());
        if(facultyModel.getId()!=null){
            facultyModel=facultyService.update(facultyModel);
        }
        mapper.writeValue(response.getOutputStream(),facultyModel);//change java object to json if write and read json to java object

    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

    }
}
