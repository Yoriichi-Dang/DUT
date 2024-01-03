package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.RoomModel;

import java.util.List;

public interface IRoomDAO {
    RoomModel findByCode(String area,String room);
    Long insert(RoomModel roomModel);
    RoomModel findById(Long id);
    List<RoomModel> findAll();
    void update(RoomModel roomModel);
    void delete(Long id);
}
