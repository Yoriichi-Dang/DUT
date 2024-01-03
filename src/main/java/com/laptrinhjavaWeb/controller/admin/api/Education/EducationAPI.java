package com.laptrinhjavaWeb.controller.admin.api.Education;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.EducationModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.UserModel;
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
import java.util.List;

@WebServlet(urlPatterns = {"/api-admin-education"})

public class EducationAPI extends HttpServlet {
    @Inject
    private IEducationService educationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
//        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String facultyCode=request.getParameter("facultyCode");
        EducationModel educationModel=new EducationModel();
        educationModel.setFacultyCode(facultyCode);
        String type=request.getParameter("type");
        if(type==null||type.isEmpty()){
            //list education
            if(facultyCode==null||facultyCode.isEmpty()){
                educationModel.setList(educationService.findAll());
            }else{
                educationModel=(educationService.findByFacultyCode(facultyCode));
            }
        }
        else{
            educationModel.setCode(request.getParameter("code"));
            //list course in education
            if(facultyCode==null||facultyCode.isEmpty()){
                educationModel=educationService.findAllCourseByCode(educationModel.getCode());
            }else{
                educationModel.setFacultyCode(facultyCode);
                educationModel=educationService.findAllEducationCourseByFacultyCode(educationModel);
            }
        }
        mapper.writeValue(response.getOutputStream(),educationModel);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        EducationModel educationModel= HttpUtil.of(request.getReader()).toModel(EducationModel.class);

        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        educationModel.setCreateBy(userModel.getUsername());
        Long id=educationService.insert(educationModel);
        List<EducationModel> educationModelList=null;
        if(id!=-1){
            educationModelList=educationService.findAll();
        }
        mapper.writeValue(response.getOutputStream(),educationModelList);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
