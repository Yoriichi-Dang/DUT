package com.laptrinhjavaWeb.controller.admin.api.Course.RegisterCourse;

import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.TimeRegisterCourseModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IRegisterCourseService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/api-admin-register-course"})
public class RegisterCourseAPI extends HttpServlet {
    @Inject
    private IRegisterCourseService registerCourseService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        TimeRegisterCourseModel timeRegisterCourseModel= HttpUtil.of(request.getReader()).toModel(TimeRegisterCourseModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        timeRegisterCourseModel.setCreateBy(userModel.getUsername());
        timeRegisterCourseModel.setDateTimeRegister(changeTimeStamp(timeRegisterCourseModel.getDateTimeRegisterStr()));
        timeRegisterCourseModel.setEndRegister(changeTimeStamp(timeRegisterCourseModel.getEndTimeRegisterStr()));
        Long id=registerCourseService.insert(timeRegisterCourseModel);
        timeRegisterCourseModel=registerCourseService.findAll();
        mapper.writeValue(response.getOutputStream(),timeRegisterCourseModel.getList());
    }
    private Timestamp changeTimeStamp(String time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
       return new Timestamp(parsedDate.getTime());
    }
//        Long id=registerCourseService.insert(timeRegisterCourseModel);
//        timeRegisterCourseModel= registerCourseService.findAll();
//        mapper.writeValue(response.getOutputStream(),timeRegisterCourseModel.getList());

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
