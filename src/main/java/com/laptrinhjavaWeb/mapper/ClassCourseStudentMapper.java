package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.ClassCourseStudentModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassCourseStudentMapper implements RowMapper<ClassCourseStudentModel> {

    @Override
    public ClassCourseStudentModel mapRow(ResultSet resultSet) {
        ClassCourseStudentModel classCourseStudentModel=new ClassCourseStudentModel();
        try{
            classCourseStudentModel.setClassCourseCode(resultSet.getString("ClassCourseCode"));
            classCourseStudentModel.setStudentCode(resultSet.getString("StudentCode"));
            classCourseStudentModel.setCreateDate(resultSet.getTimestamp("createDate"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return classCourseStudentModel;
    }
}
