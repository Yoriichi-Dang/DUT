package com.laptrinhjavaWeb.controller.api.Account;

import com.laptrinhjavaWeb.model.UserModel;
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

@WebServlet(urlPatterns = {"/api-teacher-account"})

public class TeacherAPI extends HttpServlet {
    @Inject
    private IUserService userService;
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //change password
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserModel teacherAccount= HttpUtil.of(request.getReader()).toModel(UserModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        teacherAccount.setModifiedBy(userModel.getUsername());
        teacherAccount.setStatus(1);
        if(teacherAccount.getNewPassword()!=null&&!teacherAccount.getNewPassword().isEmpty()){
            UserModel oldTeacherAccount=userService.checkAccount(teacherAccount);
            if(oldTeacherAccount!=null){
                teacherAccount.setPassword(teacherAccount.getNewPassword());
                if(userService.update(teacherAccount)==1L){
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
            if(userService.update(teacherAccount)==1L){
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(response.getOutputStream(), "success");
            }else{
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                mapper.writeValue(response.getOutputStream(), "failed");
            }
        }
    }

}
