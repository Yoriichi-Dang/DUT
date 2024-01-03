package com.laptrinhjavaWeb.controller.admin.api.Room;

import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.RoomModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.IRoomService;
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

@WebServlet(urlPatterns = {"/api-admin-room"})

public class RoomAPI extends HttpServlet {
    @Inject
    private IRoomService roomService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        RoomModel roomModel= HttpUtil.of(request.getReader()).toModel(RoomModel.class);
        UserModel userModel =(UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        roomModel.setCreateBy(userModel.getUsername());
        Long id=roomService.insert(roomModel);
        List<RoomModel>roomModelList=null;
        if(id!=-1){
            roomModelList=roomService.findAll();
        }
        mapper.writeValue(response.getOutputStream(),roomModelList);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        RoomModel roomModel=HttpUtil.of(request.getReader()).toModel(RoomModel.class);
        UserModel userModel=(UserModel)SessionUtil.getInstance().getValue(request,"USERMODEL");
        roomModel.setModifiedBy(userModel.getUsername());
        roomModel=roomService.update(roomModel);
        if(roomModel!=null){
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), "success");
        }else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            mapper.writeValue(response.getOutputStream(), "failed");
        }
//        mapper.writeValue(response.getOutputStream(),"success");//change java
//        mapper.writeValue(response.getOutputStream(),roomModelList);
    }
}
