package com.laptrinhjavaWeb.controller.api.Profile;

import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.TeacherModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IStudentService;
import com.laptrinhjavaWeb.service.ITeacherService;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/api-teacher-profile"})
@MultipartConfig
public class TeacherAPI extends HttpServlet {
    @Inject
    private ITeacherService teacherService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        Part img = request.getPart("file");
        String realPath = request.getServletContext().getRealPath("/upload/teacher");
        String filename = Paths.get(img.getSubmittedFileName()).getFileName().toString();
        if (!Files.exists(Paths.get(realPath))) {
            Files.createDirectories(Paths.get(realPath));
        }
        Long id=Long.parseLong(request.getParameter("id"));
        TeacherModel teacherModel=teacherService.findById(id);
        img.write(realPath + "\\" + filename);
        String relativePath = "/upload/teacher/" + filename;
        teacherModel.setImage(relativePath);
        teacherModel.setModifiedBy(userModel.getUsername());
        teacherModel=teacherService.update(teacherModel);
        response.sendRedirect(request.getContextPath()+"/admin-teacher-page?type=edit&id="+id);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //change password
        //update student profile
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        TeacherModel teacherModel= HttpUtil.of(request.getReader()).toModel(TeacherModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        teacherModel.setModifiedBy(userModel.getUsername());
        if(teacherService.update(teacherModel)!=null){
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), "success");
        }else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "failed");
        }
    }
}
