package com.laptrinhjavaWeb.controller.teacher.classCourse;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.dao.IClassCourseDAO;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.TeacherModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IClassCourseService;
import com.laptrinhjavaWeb.service.IRoomService;
import com.laptrinhjavaWeb.service.ITeacherService;
import com.laptrinhjavaWeb.util.FormUtil;
import com.laptrinhjavaWeb.util.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/teacher-class-course-teach-page"})
public class ClassCourseController extends HttpServlet {
    @Inject
    private IClassCourseService classCourseService;
    @Inject
    private IRoomService roomService;
    @Inject
    private ITeacherService teacherService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherModel teacherModel= FormUtil.toModel(TeacherModel.class,request);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        teacherModel=teacherService.findByCode(userModel.getUsername());
        String views="/views/teacher/class/list.jsp";
        ClassCourseModel classCourseModel=classCourseService.findAllByTeacherCode(userModel.getUsername());
        if(classCourseModel!=null&&!classCourseModel.getList().isEmpty()){
            request.setAttribute("classcourses",classCourseModel.getList());
            request.setAttribute("rooms",roomService.findAll());
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,teacherModel);
        rd.forward(request,response);
    }
}
