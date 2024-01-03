package com.laptrinhjavaWeb.controller.admin.api.Course;

import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.ICourseService;
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
import java.sql.Timestamp;
import java.util.List;

@WebServlet(urlPatterns = {"/api-admin-course"})

public class CourseAPI extends HttpServlet {
    @Inject
    private ICourseService courseService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json");
        String facultyCode=request.getParameter("facultyCode");
        CourseModel courseModel=new CourseModel();
        if(facultyCode==null||facultyCode.isEmpty()){
            courseModel.setList(courseService.findAll());
        }else{
            courseModel=courseService.findByFacultyCode(facultyCode);
        }
        mapper.writeValue(response.getOutputStream(),courseModel);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CourseModel courseModel= HttpUtil.of(request.getReader()).toModel(CourseModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        courseModel.setCreateBy(userModel.getUsername());
        Long id=courseService.insert(courseModel);
        List<CourseModel> courseModels=courseService.findAll();
        mapper.writeValue(response.getOutputStream(),courseModels);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CourseModel courseModel= HttpUtil.of(request.getReader()).toModel(CourseModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        courseModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        courseModel.setModifiedBy(userModel.getUsername());
        Long id=courseService.update(courseModel);
        if(id==-1L){
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "failed");
        }else{
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), "success");
        }
    }
}
