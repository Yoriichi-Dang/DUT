package com.laptrinhjavaWeb.controller.student.ClassCourse;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IClassCourseService;
import com.laptrinhjavaWeb.service.IFacultyService;
import com.laptrinhjavaWeb.service.IStudentService;
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
import java.util.List;

@WebServlet(urlPatterns = {"/student-class-course-page"})

public class ClassCourseController extends HttpServlet {
    @Inject
    private IClassCourseService classCourseService;
    @Inject
    private IFacultyService facultyService;
    @Inject
    private IStudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String views="/views/student/ClassCourse/list.jsp";
        ClassCourseModel classCourseModel= FormUtil.toModel(ClassCourseModel.class,request);
        List<ClassCourseModel>list=classCourseService.findAll();
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        StudentModel studentModel=studentService.findStudentByCode(userModel.getUsername());
        if(classCourseModel.getType()==null){
            request.setAttribute("faculties",facultyService.findAll());
            request.setAttribute(SystemConstant.LIST,list);
        }
        else if(classCourseModel.getType().equals(SystemConstant.CLASSCOURSE)){
            ClassCourseModel model=classCourseService.findAllStudentInClassCouse(classCourseModel.getCode());
            if(classCourseModel.getList()!=null){
                classCourseModel=model;
                views="/views/student/ClassCourse/listStudentClassCourse.jsp";
            }
            request.setAttribute("liststudent",classCourseModel);
        }
        request.setAttribute(SystemConstant.MODEL,studentModel);
        RequestDispatcher rd= request.getRequestDispatcher(views);
        rd.forward(request,response);
    }
}
