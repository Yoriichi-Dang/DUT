package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IRoomDAO;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.RoomModel;
import com.laptrinhjavaWeb.service.IRoomService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class RoomService implements IRoomService {
    @Inject
    private IRoomDAO roomDAO;
    @Override
    public RoomModel  findByCode(String area,String room)  {
        return roomDAO.findByCode(area,room);
    }

    @Override
    public Long insert(RoomModel roomModel) {
        return roomDAO.insert(roomModel);
    }

    @Override
    public RoomModel findById(Long id) {
        return roomDAO.findById(id);
    }

    @Override
    public List<RoomModel> findAll() {
       return roomDAO.findAll();
    }

    @Override
    public RoomModel update(RoomModel roomModel) {
        RoomModel oldRoom=roomDAO.findById(roomModel.getId());
        if(oldRoom.getCreateBy()==null||oldRoom.getCreateBy().isEmpty())oldRoom.setCreateBy(roomModel.getModifiedBy());
        if(oldRoom.getCreateDate()==null)oldRoom.setCreateDate(new Timestamp(System.currentTimeMillis()));
        if(roomModel.getAreaCode().isEmpty())roomModel.setCode(oldRoom.getAreaCode());
        if(roomModel.getRoomCode().isEmpty())roomModel.setRoomCode(oldRoom.getRoomCode());
        roomModel.setCreateDate(oldRoom.getCreateDate());
        roomModel.setCreateBy(oldRoom.getCreateBy());
        roomModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//        System.out.println(facultyModel.getId()+" "+facultyModel.getCode()+" "+facultyModel.getName());
//        System.out.println(roomModel.getCreateBy()+roomModel.getCreateDate()+roomModel.getModifiedBy()+roomModel.getModifiedDate());
        roomDAO.update(roomModel);
        return roomDAO.findById(roomModel.getId());
    }

    @Override
    public void delete(Long id) {
        roomDAO.delete(id);
    }
}
