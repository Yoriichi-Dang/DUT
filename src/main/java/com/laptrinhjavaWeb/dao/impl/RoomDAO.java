package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.IRoomDAO;
import com.laptrinhjavaWeb.mapper.RoomMapper;
import com.laptrinhjavaWeb.model.RoomModel;

import java.util.List;

public class RoomDAO extends AbstractDAO<RoomModel> implements IRoomDAO {
    @Override
    public RoomModel findByCode(String area,String room) {
        StringBuilder sql = new StringBuilder("select * from room where Area=? and Code=?");
       List<RoomModel>roomModelList=query(sql.toString(),new RoomMapper(),area,room);
       return roomModelList.get(0)!=null?roomModelList.get(0):null;
    }

    @Override
    public Long insert(RoomModel roomModel) {
        StringBuilder sql = new StringBuilder("insert into room(Area,Code,seat,createBy) values(?,?,?,?);");
        Long id = insert(sql.toString(), roomModel.getAreaCode(), roomModel.getRoomCode(), roomModel.getSeat(),
                roomModel.getCreateBy());
        return id;
    }

    @Override
    public RoomModel findById(Long id) {
        StringBuilder sql = new StringBuilder("select * from room where id=?");
        return findById(sql.toString(), new RoomMapper(), id);
    }

    @Override
    public List<RoomModel> findAll() {
        StringBuilder sql = new StringBuilder("select * from room");
        // if(pageable.getSorter()!=null&&!StringUtils.isNullOrEmpty(pageable.getSorter().getSortName())){
        // sql.append(" order by "+pageable.getSorter().getSortName()+"
        // "+pageable.getSorter().getSortBy());
        // }
        // if(pageable.getOffset()!=null&&pageable.getLimit()!=null){
        // sql.append(" limit "+pageable.getOffset()+", "+pageable.getLimit());
        // }
        return query(sql.toString(), new RoomMapper());
    }

    @Override
    public void update(RoomModel roomModel) {
        String sql = "update room set Area=?,Code=?,seat=?, createDate=?,createBy=? ,modifiedDate=?,modifiedBy=? where id=?";
        update(sql, roomModel.getAreaCode(), roomModel.getRoomCode(), roomModel.getSeat(), roomModel.getCreateDate(),
                roomModel.getCreateBy(), roomModel.getModifiedDate(), roomModel.getModifiedBy(), roomModel.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from room where id=?";
        update(sql, id);
    }
}
