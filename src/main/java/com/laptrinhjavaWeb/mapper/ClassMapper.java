package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.FacultyModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassMapper implements RowMapper<ClassModel>{
    @Override
    public ClassModel mapRow(ResultSet resultSet) {
        try{
            ClassModel classModel=new ClassModel();
            classModel.setId(resultSet.getLong("id"));
            classModel.setCode(resultSet.getString("code"));
//            userModel.setCreateBy(resultSet.getString("createBy"));
//            userModel.setCreateDate(resultSet.getTimestamp("createDate"));

//            if(resultSet.getTimestamp("modifiesDate")!=null){
//                userModel.setModifiedDate(resultSet.getTimestamp("modifiesDate"));
//            }
//            if(resultSet.getString("modifiesBy")!=null){
//                userModel.setModifiedBy(resultSet.getString("modifiesBy"));
//            }
            return classModel;
        }catch (SQLException e){
            return null;
        }
    }
}
