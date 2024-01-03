package com.laptrinhjavaWeb.controller.teacher.api.classCourse;

import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.model.RoomModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IRegisterRoomService;
import com.laptrinhjavaWeb.service.IRoomService;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/api-register-class-course-teach"})

public class ClassCourseRegisterAPI extends HttpServlet {
    @Inject
    private IRegisterRoomService registerRoomService;
    @Inject
    private IRoomService roomService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        ClassCourseModel classCourseModel=new ClassCourseModel();
        classCourseModel.setStartLesson(Integer.parseInt(request.getParameter("startLesson")));
        classCourseModel.setEndLesson(Integer.parseInt(request.getParameter("endLesson"))-1);
        RegisterRoomModel registerRoomModel=new RegisterRoomModel();
        RoomModel roomModel=new RoomModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=request.getParameter("date");
        registerRoomModel.setClassCourseModel(classCourseModel);
        try{
            registerRoomModel.setDate(LocalDate.parse(dateStr));
            roomModel =registerRoomService.findByRoomByDate(registerRoomModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(registerRoomModel!=null){
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(),roomModel.getList()) ;
        }
        else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "failed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        ClassCourseModel classCourseModel= HttpUtil.of(request.getReader()).toModel(ClassCourseModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        RegisterRoomModel registerRoomModel=new RegisterRoomModel();
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
        classCourseModel.setAreaCode(area.toString());
        classCourseModel.setNumberRoomCode(number.toString());
        classCourseModel.setRoomModel(roomService.findByCode(classCourseModel.getAreaCode(),classCourseModel.getNumberRoomCode()));
        Long id=-1L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        registerRoomModel.setClassCourseCode(classCourseModel.getCode());
        registerRoomModel.setClassCourseModel(classCourseModel);
        try{
            String formattedDate = sdf.format(classCourseModel.getDate());
            registerRoomModel.setDate(LocalDate.parse(formattedDate));
            id=registerRoomService.registerClassCourseTeachInDate(registerRoomModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(id!=-1L){
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), "success");
        }else if(id==-2L){
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "đã có người đăng ký phòng này");
        }
        else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "failed");
        }
    }
}
