package com.laptrinhjavaWeb.controller.admin;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.RoomModel;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.service.IRoomService;
import com.laptrinhjavaWeb.util.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-room-page"})

public class RoomController extends HttpServlet {
    @Inject
    private IRoomService roomService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomModel roomModel= FormUtil.toModel(RoomModel.class,request);
        String views="";
        if(roomModel.getType()==null||roomModel.getType().equals(SystemConstant.LIST)){
            roomModel.setList(roomService.findAll());
            views="/views/admin/Room/list.jsp";
        }
        else if(roomModel.getType().equals(SystemConstant.EDIT)){
            if(roomModel.getId()!=null){
                roomModel=roomService.findById(roomModel.getId());
                views="/views/admin/Room/edit.jsp";
            }
        }else if(roomModel.getType().equals(SystemConstant.DELETE)){
            if(roomModel.getId()!=null){
                roomService.delete(roomModel.getId());
                roomModel.setList(roomService.findAll());
                views="/views/admin/Room/list.jsp";
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(views);
        request.setAttribute(SystemConstant.MODEL,roomModel);
        rd.forward(request,response);
    }
}
