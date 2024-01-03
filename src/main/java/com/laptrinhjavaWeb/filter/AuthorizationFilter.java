package com.laptrinhjavaWeb.filter;


import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.util.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context=filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String url=request.getRequestURI();
        UserModel userModel= (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if(url.startsWith("/DUT/admin")||url.startsWith("/admin")){
            if(userModel!=null){
                System.out.println(userModel.getRoleCode());
                if(userModel.getRoleCode().equalsIgnoreCase(SystemConstant.ADMIN)){
                    filterChain.doFilter(servletRequest,servletResponse);
                }
                else{
                    System.out.println("Error role");
                    response.sendRedirect(request.getContextPath()+"/login-page?action=login&message=not_permision");
                }
            }
            else{
                response.sendRedirect(request.getContextPath()+"/login-page?action=login&message=not_login");
            }
        }
        else if(url.startsWith("/DUT/student")||url.startsWith("/student")){
            if(userModel!=null){
                System.out.println(userModel.getRoleCode());
                if(userModel.getRoleCode().equalsIgnoreCase(SystemConstant.STUDENT)){
                    filterChain.doFilter(servletRequest,servletResponse);
                }
                else{
                    System.out.println("Error role");
                    response.sendRedirect(request.getContextPath()+"/login-page?action=login&message=not_permision");
                }
            }
            else{
                response.sendRedirect(request.getContextPath()+"/login-page?action=login&message=not_login");
            }
        }
        else if(url.startsWith("/DUT/teacher")||url.startsWith("/teacher")){
            if(userModel!=null){
                System.out.println(userModel.getRoleCode());
                if(userModel.getRoleCode().equalsIgnoreCase(SystemConstant.TEACHER)){
                    filterChain.doFilter(servletRequest,servletResponse);
                }
                else{
                    System.out.println("Error role");
                    response.sendRedirect(request.getContextPath()+"/login-page?action=login&message=not_permision");
                }
            }
            else{
                response.sendRedirect(request.getContextPath()+"/login-page?action=login&message=not_login");
            }
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

