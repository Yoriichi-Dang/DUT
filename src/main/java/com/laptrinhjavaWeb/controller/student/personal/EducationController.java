package com.laptrinhjavaWeb.controller.student.personal;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.dao.ICourseDependentDAO;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.CourseDependentModel;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.ICourseDependentService;
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

@WebServlet(urlPatterns = {"/student-education-page"})
public class EducationController extends HttpServlet {
    @Inject
    private ICourseDependentService courseDependentService;
    @Inject
    private IStudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String views="/views/student/personal/education.jsp";
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        StudentModel studentModel=studentService.findStudentByCode(userModel.getUsername());
        CourseDependentModel courseDependentModel=courseDependentService.findAllCourseInEducationByStudentCode(userModel.getUsername());
        //danh sachs courseDependentModel trong mỗi phần tử trong list là 1 học phần CourseModel và danh sách các học phần phụ thuộc
        request.setAttribute("educationCourse",courseDependentModel.getList());// lấy danh sách các học phần
        request.setAttribute(SystemConstant.MODEL,studentModel);
        RequestDispatcher rd= request.getRequestDispatcher(views);
        rd.forward(request,response);
    }
}
