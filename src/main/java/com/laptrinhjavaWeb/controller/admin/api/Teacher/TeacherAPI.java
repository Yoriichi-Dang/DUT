package com.laptrinhjavaWeb.controller.admin.api.Teacher;

import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.TeacherModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.ITeacherService;
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

@WebServlet(urlPatterns = {"/api-admin-teacher"})

public class TeacherAPI extends HttpServlet {
    @Inject
    private ITeacherService teacherService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        TeacherModel teacherModel= HttpUtil.of(request.getReader()).toModel(TeacherModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        teacherModel.setCreateBy(userModel.getUsername());
        if(teacherModel.getSex().equals("1")){
            teacherModel.setSex("nam");
        }else if(teacherModel.getSex().equals("2")){
            teacherModel.setSex("nữ");
        }
        Long id=teacherService.insert(teacherModel);
        List<TeacherModel> teacherModelList=null;
        if(id!=-1){
            teacherModelList=teacherService.findAll();
        }
        mapper.writeValue(response.getOutputStream(),teacherModelList);
    }
}
