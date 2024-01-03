package com.laptrinhjavaWeb.controller.admin.api.Class;

import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IAcademicService;
import com.laptrinhjavaWeb.service.IClassService;
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

@WebServlet(urlPatterns = {"/api-admin-class"})

public class ClassAPI extends HttpServlet {
    @Inject
    private IClassService classService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ClassModel classModel= HttpUtil.of(request.getReader()).toModel(ClassModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        classModel.setCreateBy(userModel.getUsername());
        Long id=classService.insert(classModel);
        List<ClassModel> classModelList=null;
        if(id!=-1){
            classModelList=classService.findAll();
        }
        mapper.writeValue(response.getOutputStream(),classModelList);
    }
}
