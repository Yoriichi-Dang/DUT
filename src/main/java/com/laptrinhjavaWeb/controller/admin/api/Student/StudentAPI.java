package com.laptrinhjavaWeb.controller.admin.api.Student;

import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IStudentService;
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

@WebServlet(urlPatterns = {"/api-admin-student"})

public class StudentAPI  extends HttpServlet {
    @Inject
    private IStudentService studentService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        StudentModel studentModel= HttpUtil.of(request.getReader()).toModel(StudentModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        studentModel.setCreateBy(userModel.getUsername());
        if(studentModel.getSex().equals("1")){
            studentModel.setSex("nam");
        }else if(studentModel.getSex().equals("2")){
            studentModel.setSex("nữ");
        }
        Long id=studentService.insert(studentModel);
        List<StudentModel> studentModelList=null;
        if(id!=-1){
            studentModelList=studentService.findAll();
        }
        mapper.writeValue(response.getOutputStream(),studentModelList);
    }
}
