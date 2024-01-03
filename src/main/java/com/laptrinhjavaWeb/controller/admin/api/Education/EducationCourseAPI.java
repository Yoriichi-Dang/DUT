package com.laptrinhjavaWeb.controller.admin.api.Education;

import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.EducationModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.ICourseService;
import com.laptrinhjavaWeb.service.IEducationService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api-admin-education-course"})

public class EducationCourseAPI  extends HttpServlet {
    @Inject
    private IEducationService educationService;
    @Inject
    private ICourseService courseService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json");
        String codeEducation=request.getParameter("code");
        String facultyCode=request.getParameter("facultyCode");
        CourseModel courseModel=new CourseModel();
        EducationModel educationModel=educationService.findAllCourseByCode(codeEducation);
        if(facultyCode==null||facultyCode.isEmpty()){
            courseModel.setList(courseService.findAll());
        }else{
            courseModel=courseService.findByFacultyCode(facultyCode);
        }
        for(int i=0;i<educationModel.getCourseModelList().size();i++){
            for(int j=0;j<courseModel.getList().size();j++){
                if(educationModel.getCourseModelList().get(i).getCode().equals(courseModel.getList().get(j).getCode())){
                    courseModel.getList().remove(j);
                    break;
                }
            }
        }
        mapper.writeValue(response.getOutputStream(),courseModel);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        EducationModel educationModel= HttpUtil.of(request.getReader()).toModel(EducationModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        educationModel.setCreateBy(userModel.getUsername());
        Long id=educationService.insertEducationCourse(educationModel);
        CourseModel courseModel=new CourseModel();
        if(id!=-1L){
            educationModel=educationService.findAllCourseByCode(educationModel.getCode());
             courseModel.setList(courseService.findAll());
            for(int i=0;i<educationModel.getCourseModelList().size();i++){
                for(int j=0;j<courseModel.getList().size();j++){
                    if(educationModel.getCourseModelList().get(i).getCode().equals(courseModel.getList().get(j).getCode())){
                        courseModel.getList().remove(j);
                        break;
                    }
                }
            }
            mapper.writeValue(response.getOutputStream(),courseModel);
        }
        else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "fail");
        }
    }

}
