package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IRegisterRoomDAO;
import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.model.RoomModel;
import com.laptrinhjavaWeb.model.TimeRegisterCourseModel;
import com.laptrinhjavaWeb.service.IRegisterCourseService;
import com.laptrinhjavaWeb.service.IRegisterRoomService;

import javax.inject.Inject;
import java.sql.Timestamp;

public class RegisterRoomService implements IRegisterRoomService {
    @Inject
    private IRegisterRoomDAO registerRoomDAO;


    @Override
    public RegisterRoomModel findScheduleTeach(String teacherCode, RegisterRoomModel registerRoomModel) {
        return registerRoomDAO.findScheduleTeach(teacherCode,registerRoomModel);//send date
    }

    @Override
    public Long deleteClassCourseTeachInDate(RegisterRoomModel registerRoomModel) {
        return registerRoomDAO.deleteClassCourseTeachInDate(registerRoomModel);
    }

    @Override
    public Long registerClassCourseTeachInDate(RegisterRoomModel registerRoomModel) {
        return registerRoomDAO.registerClassCourseTeachInDate(registerRoomModel);
    }

    @Override
    public RegisterRoomModel findByModel(RegisterRoomModel registerRoomModel) {
        return registerRoomDAO.findByModel(registerRoomModel);
    }

    @Override
    public RoomModel findByRoomByDate(RegisterRoomModel registerRoomModel) {
        return registerRoomDAO.findByRoomByDate(registerRoomModel);
    }
}
