package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.RoomModel;

import java.util.List;

public interface IRoomService {
    RoomModel  findByCode(String area,String room) ;
    Long insert(RoomModel roomModel);
    RoomModel findById(Long id);
    List<RoomModel> findAll();
    RoomModel update(RoomModel roomModel);
    void delete(Long id);
}
