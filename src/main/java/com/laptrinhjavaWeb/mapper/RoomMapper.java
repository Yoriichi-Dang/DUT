package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.RoomModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper  implements RowMapper<RoomModel> {
    @Override
    public RoomModel mapRow(ResultSet resultSet) {
        try{
            RoomModel roomModel=new RoomModel();
            roomModel.setId(resultSet.getLong("id"));
            roomModel.setAreaCode(resultSet.getString("Area"));
            roomModel.setRoomCode(resultSet.getString("Code"));
            roomModel.setSeat(resultSet.getInt("seat"));
//            userModel.setCreateBy(resultSet.getString("createBy"));
//            userModel.setCreateDate(resultSet.getTimestamp("createDate"));

//            if(resultSet.getTimestamp("modifiesDate")!=null){
//                userModel.setModifiedDate(resultSet.getTimestamp("modifiesDate"));
//            }
//            if(resultSet.getString("modifiesBy")!=null){
//                userModel.setModifiedBy(resultSet.getString("modifiesBy"));
//            }
            return roomModel;
        }catch (SQLException e){
            return null;
        }
    }
}
