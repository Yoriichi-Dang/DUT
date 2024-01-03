package com.laptrinhjavaWeb.controller.teacher.api.personal;

import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IRegisterRoomService;
import com.laptrinhjavaWeb.util.HttpUtil;
import com.laptrinhjavaWeb.util.SessionUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(urlPatterns = {"/api-off-teach"})
public class offTeachAPI extends HttpServlet {
    @Inject
    private IRegisterRoomService registerRoomService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        ClassCourseModel classCourseModel= HttpUtil.of(request.getReader()).toModel(ClassCourseModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        RegisterRoomModel registerRoomModel=new RegisterRoomModel();
        registerRoomModel.setClassCourseCode(classCourseModel.getCode());
        Long id=1L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            String formattedDate = sdf.format(classCourseModel.getDate());
            registerRoomModel.setDate(LocalDate.parse(formattedDate));
            id=registerRoomService.deleteClassCourseTeachInDate(registerRoomModel);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(id!=-1L){
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), "success");
        }else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "failed");
        }
    }
}
