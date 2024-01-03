package com.laptrinhjavaWeb.controller.api.Account;

import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IStudentService;
import com.laptrinhjavaWeb.service.IUserService;
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

@WebServlet(urlPatterns = {"/api-student-account"})

public class StudentAPI extends HttpServlet {
    @Inject
    private IUserService userService;
    @Inject
    private IStudentService studentService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //update student profile
//        ObjectMapper mapper=new ObjectMapper();
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        StudentModel studentModel= HttpUtil.of(request.getReader()).toModel(StudentModel.class);
//        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
//        studentModel.setModifiedBy(userModel.getUsername());
////        studentService.update(studentModel);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //change password
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserModel studentAccount= HttpUtil.of(request.getReader()).toModel(UserModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        studentAccount.setModifiedBy(userModel.getUsername());
        studentAccount.setStatus(1);
        if(studentAccount.getNewPassword()!=null&&!studentAccount.getNewPassword().isEmpty()){
            UserModel oldStudentAccount=userService.checkAccount(studentAccount);
            if(oldStudentAccount!=null){
                studentAccount.setPassword(studentAccount.getNewPassword());
                if(userService.update(studentAccount)==1L){
                    response.setStatus(HttpServletResponse.SC_OK);
                    mapper.writeValue(response.getOutputStream(), "success");
                }else{
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                    mapper.writeValue(response.getOutputStream(), "failed");
                }
            }
            else{
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                mapper.writeValue(response.getOutputStream(), "wrong old password");
            }
        }else{
            if(userService.update(studentAccount)==1L){
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(response.getOutputStream(), "success");
            }else{
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                mapper.writeValue(response.getOutputStream(), "failed");
            }
        }
    }
}
