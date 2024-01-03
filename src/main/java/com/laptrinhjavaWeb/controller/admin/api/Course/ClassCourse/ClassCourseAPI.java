package com.laptrinhjavaWeb.controller.admin.api.Course.ClassCourse;

import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IClassCourseService;
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

@WebServlet(urlPatterns = {"/api-admin-class-course"})

public class ClassCourseAPI  extends HttpServlet {
    @Inject
    private IClassCourseService classCourseService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ClassCourseModel classCourseModel= HttpUtil.of(request.getReader()).toModel(ClassCourseModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        classCourseModel.setCreateBy(userModel.getUsername());
        Long id=classCourseService.insert(classCourseModel);
        if(id!=-1L){
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), "success");
        }
        else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "failed");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ClassCourseModel classCourseModel= HttpUtil.of(request.getReader()).toModel(ClassCourseModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        classCourseModel.setModifiedBy(userModel.getUsername());
        String roomCode = classCourseModel.getRoomCode();
        StringBuilder area = new StringBuilder();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < roomCode.length(); i++) {
            if (Character.isDigit(roomCode.charAt(i))) {
                number.append(roomCode.charAt(i));
            } else {
                area.append(roomCode.charAt(i));
            }
        }
        System.out.println(number);
        System.out.println(area);
        classCourseModel.setAreaCode(area.toString());
        classCourseModel.setNumberRoomCode(number.toString());
        classCourseModel.setModifiedBy(userModel.getUsername());
        Long id=classCourseService.update(classCourseModel);
        if(id==-1L){
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "failed");
        }else if(id==-2L){
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "Phòng không đủ chỗ");
        }
        else{
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), "success");
        }
    }
}
