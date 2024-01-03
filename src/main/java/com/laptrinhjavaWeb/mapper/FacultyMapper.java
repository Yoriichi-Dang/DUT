package com.laptrinhjavaWeb.mapper;


import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.RoleModel;
import com.laptrinhjavaWeb.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyMapper implements RowMapper<FacultyModel>{
    @Override
    public FacultyModel mapRow(ResultSet resultSet) {
        try{
            FacultyModel facultyModel=new FacultyModel();
            facultyModel.setId(resultSet.getLong("id"));
            facultyModel.setCode(resultSet.getString("code"));
            facultyModel.setName(resultSet.getString("name"));
//            userModel.setCreateBy(resultSet.getString("createBy"));
//            userModel.setCreateDate(resultSet.getTimestamp("createDate"));

//            if(resultSet.getTimestamp("modifiesDate")!=null){
//                userModel.setModifiedDate(resultSet.getTimestamp("modifiesDate"));
//            }
//            if(resultSet.getString("modifiesBy")!=null){
//                userModel.setModifiedBy(resultSet.getString("modifiesBy"));
//            }
            return facultyModel;
        }catch (SQLException e){
            return null;
        }
    }
}
