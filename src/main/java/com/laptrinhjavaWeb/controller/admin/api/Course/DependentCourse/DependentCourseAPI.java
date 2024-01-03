package com.laptrinhjavaWeb.controller.admin.api.Course.DependentCourse;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.ICourseService;
import com.laptrinhjavaWeb.util.FormUtil;
import com.laptrinhjavaWeb.util.HttpUtil;
import com.laptrinhjavaWeb.util.SessionUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(urlPatterns = {"/api-admin-dependent-course"})

public class DependentCourseAPI extends HttpServlet {
    @Inject
    private ICourseService courseService;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CourseModel courseModel= HttpUtil.of(request.getReader()).toModel(CourseModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        courseModel.setCreateBy(userModel.getUsername());
        Long id=courseService.insertDependentCourse(courseModel);
        courseModel=courseService.findAllCourseDependent(courseModel.getCode());
        mapper.writeValue(response.getOutputStream(),courseModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json");
        CourseModel courseModel= HttpUtil.of(request.getReader()).toModel(CourseModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        courseModel.setModifiedBy(userModel.getUsername());
        courseModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if(courseModel.getId()!=null){
            Long id=courseService.updateDependentCourse(courseModel);
            if(id==-1L){
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                mapper.writeValue(response.getOutputStream(),"failed");
            }else{
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(response.getOutputStream(),"success");
            }
        }else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(),"id is null");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json");
        CourseModel courseModel= HttpUtil.of(request.getReader()).toModel(CourseModel.class);
        if(courseModel.getId()!=null){
           Long  id=courseService.deleteDepentdentCourse(courseModel.getId());
            if(id==-1L){
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                mapper.writeValue(response.getOutputStream(),"failed");
            }else{
                courseModel=courseService.findAllCourseDependent(courseModel.getCode());
                mapper.writeValue(response.getOutputStream(),courseModel);
            }
        }else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(),"id is null");
        }

    }

}
