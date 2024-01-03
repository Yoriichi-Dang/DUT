package com.laptrinhjavaWeb.controller.web;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.NotificationTeachModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.INotificationTeachService;
import com.laptrinhjavaWeb.service.IUserService;
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

@WebServlet(urlPatterns = {"/home-page","/login-page","/register-page","/logout-page"})

public class HomeController extends HttpServlet {
    @Inject
    private IUserService userService;
    @Inject
    private INotificationTeachService notificationTeachService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action!=null&&action.equals("login")){
            String message=request.getParameter("message");
//            String alert=request.getParameter("alert");
            if(message!=null){
                if(message.equals("not_permision")){
                    RequestDispatcher rd=request.getRequestDispatcher("/views/login/error_access.jsp");
                    rd.forward(request,response);
                }
                else if(message.equals("not_login")){
                    RequestDispatcher rd=request.getRequestDispatcher("/views/login/not_login.jsp");
                    rd.forward(request,response);
                }
            }
            RequestDispatcher rd=request.getRequestDispatcher("/views/login/login.jsp");
            rd.forward(request,response);
        }else if(action!=null&&action.equals("logout")){
            UserModel userModel=(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
            SessionUtil.getInstance().removeValue(request,"USERMODEL");
            response.sendRedirect(request.getContextPath()+"/home-page");
        }
        else{
            List<NotificationTeachModel>list=notificationTeachService.findAll();
            request.setAttribute("listNotification",list);
            RequestDispatcher rd=request.getRequestDispatcher("/views/web/notification.jsp");
            rd.forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");

        if(action!=null&&action.equals("login")){
            UserModel userModel= FormUtil.toModel(UserModel.class,request);
            userModel=userService.checkAccount(userModel);
            if(userModel!=null){
                SessionUtil.getInstance().putValue(request,"USERMODEL",userModel);//save session user login
                response.sendRedirect(request.getContextPath()+"/home-page");
            }else{
//                response.sendRedirect(request.getContextPath()+"/login-page?action=login&message=username_password_invalid&alert=danger");
                response.sendRedirect(request.getContextPath()+"/login-page?action=login");
            }
        }
    }
}
