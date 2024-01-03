package com.laptrinhjavaWeb.controller.api.Profile;

import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IStudentService;
import com.laptrinhjavaWeb.util.HttpUtil;
import com.laptrinhjavaWeb.util.SessionUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/api-student-profile"})
@MultipartConfig
public class StudentAPI extends HttpServlet {
    @Inject
    private IStudentService studentService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        Part img = request.getPart("file");
        String realPath = request.getServletContext().getRealPath("/upload/student");
        String filename = Paths.get(img.getSubmittedFileName()).getFileName().toString();
        if (!Files.exists(Paths.get(realPath))) {
            Files.createDirectories(Paths.get(realPath));
        }
        Long id=Long.parseLong(request.getParameter("id"));
        StudentModel studentModel=studentService.findStudentById(id);
        System.out.println(realPath+"\\"+filename);
        img.write(realPath + "\\" + filename);
        String relativePath = "/upload/student/" + filename;
        studentModel.setImage(relativePath);
        studentModel.setModifiedBy(userModel.getUsername());
        studentModel=studentService.update(studentModel);
       response.sendRedirect(request.getContextPath()+"/admin-student-page?type=edit&id="+id);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //change password
        //update student profile
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        StudentModel studentModel= HttpUtil.of(request.getReader()).toModel(StudentModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        studentModel.setModifiedBy(userModel.getUsername());
        if(studentService.update(studentModel)!=null){
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(response.getOutputStream(), "success");
        }else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
             mapper.writeValue(response.getOutputStream(), "failed");
        }
    }
}
