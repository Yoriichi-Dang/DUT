package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.model.RoomModel;

public interface IRegisterRoomService {
    RegisterRoomModel findScheduleTeach(String teacherCode, RegisterRoomModel registerRoomModel);
    Long deleteClassCourseTeachInDate(RegisterRoomModel registerRoomModel);
    Long registerClassCourseTeachInDate(RegisterRoomModel registerRoomModel);
    RegisterRoomModel findByModel(RegisterRoomModel registerRoomModel);
    RoomModel findByRoomByDate(RegisterRoomModel registerRoomModel);
}
