package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.model.RoomModel;

import java.util.List;

public interface IRegisterRoomDAO {
    RegisterRoomModel findAllByClassCourseCode(String classCourseCode);
    RegisterRoomModel findScheduleTeach(String teacherCode,RegisterRoomModel registerRoomModel);
    Long deleteClassCourseTeachInDate(RegisterRoomModel registerRoomModel);
    Long registerClassCourseTeachInDate(RegisterRoomModel registerRoomModel);
    RegisterRoomModel findByModel(RegisterRoomModel registerRoomModel);
    RoomModel findByRoomByDate(RegisterRoomModel registerRoomModel);
}
