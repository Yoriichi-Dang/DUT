package com.laptrinhjavaWeb.controller.teacher.personal;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.model.TeacherModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IRegisterRoomService;
import com.laptrinhjavaWeb.service.ITeacherService;
import com.laptrinhjavaWeb.service.IUserService;
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
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/teacher-schelude-teach-page"})

public class ScheduleTeachController extends HttpServlet {
    @Inject
    private IRegisterRoomService registerRoomService;
    @Inject
    private ITeacherService teacherService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherModel teacherModel= FormUtil.toModel(TeacherModel.class,request);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");

        String views="/views/teacher/personal/scheduleTeach.jsp";
        LocalDate date=LocalDate.now();
        try{
            if (request.getParameter("date") != null) {
                date = LocalDate.parse(request.getParameter("date"));
                System.out.println(date);
                // Continue processing with date
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(date==null)date=LocalDate.now();
        RegisterRoomModel registerRoomModel=new RegisterRoomModel();
        RegisterRoomModel model=new RegisterRoomModel();
        if(teacherModel.getType()==null){
            teacherModel=teacherService.findByCode(userModel.getUsername());
            // hiển thi cac mon day tron ngay
            registerRoomModel=new RegisterRoomModel();
            registerRoomModel.setDate(date);
            model=registerRoomService.findScheduleTeach(userModel.getUsername(),registerRoomModel);
            if(model!=null&&!model.getList().isEmpty()){
                registerRoomModel.setList(model.getList());
            }
        }else if(registerRoomModel.getDate()!=null){
            model=registerRoomService.findScheduleTeach(userModel.getUsername(),registerRoomModel);
            if(model!=null&&!model.getList().isEmpty()){
                registerRoomModel.setList(model.getList());
            }
        }
        request.setAttribute("scheduleClassCourse",registerRoomModel);
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,teacherModel);
        rd.forward(request,response);
    }
}
