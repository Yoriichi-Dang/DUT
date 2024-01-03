package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.ClassModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AcademicMapper implements RowMapper<AcademicModel>{
    @Override
    public AcademicModel mapRow(ResultSet resultSet) {
        try{
            AcademicModel academicModel=new AcademicModel();
            academicModel.setId(resultSet.getLong("id"));
            academicModel.setCode(resultSet.getString("code"));
            academicModel.setName(resultSet.getString("name"));
//            userModel.setCreateBy(resultSet.getString("createBy"));
//            userModel.setCreateDate(resultSet.getTimestamp("createDate"));

//            if(resultSet.getTimestamp("modifiesDate")!=null){
//                userModel.setModifiedDate(resultSet.getTimestamp("modifiesDate"));
//            }
//            if(resultSet.getString("modifiesBy")!=null){
//                userModel.setModifiedBy(resultSet.getString("modifiesBy"));
//            }
            return academicModel;
        }catch (SQLException e){
            return null;
        }
    }
}
