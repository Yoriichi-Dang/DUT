package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.RoleModel;
import com.laptrinhjavaWeb.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel>{
    @Override
    public UserModel mapRow(ResultSet resultSet) {
        try{
            UserModel userModel=new UserModel();
            userModel.setId(resultSet.getLong("id"));
            userModel.setUsername(resultSet.getString("username"));
            userModel.setPassword(resultSet.getString("password"));
            userModel.setStatus(resultSet.getInt("status"));
            try{
                userModel.setRoleCode(resultSet.getString("roleCode"));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
//            userModel.setCreateBy(resultSet.getString("createBy"));
//            userModel.setCreateDate(resultSet.getTimestamp("createDate"));

//            if(resultSet.getTimestamp("modifiesDate")!=null){
//                userModel.setModifiedDate(resultSet.getTimestamp("modifiesDate"));
//            }
//            if(resultSet.getString("modifiesBy")!=null){
//                userModel.setModifiedBy(resultSet.getString("modifiesBy"));
//            }
            return userModel;
        }catch (SQLException e){
            return null;
        }
    }
}
