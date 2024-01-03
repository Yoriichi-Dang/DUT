package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.TimeRegisterCourseModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeRegisterCourseMapper implements RowMapper<TimeRegisterCourseModel> {

    @Override
    public TimeRegisterCourseModel mapRow(ResultSet resultSet) {
        TimeRegisterCourseModel timeRegisterCourseModel=new TimeRegisterCourseModel();
        try{
            timeRegisterCourseModel.setId(resultSet.getLong("id"));
            timeRegisterCourseModel.setTypeRegister(resultSet.getString("typeRegister"));
            timeRegisterCourseModel.setValue(resultSet.getString("value"));
            timeRegisterCourseModel.setDateTimeRegister(resultSet.getTimestamp("datetimeRegister"));
            timeRegisterCourseModel.setEndRegister(resultSet.getTimestamp("endRegister"));
            timeRegisterCourseModel.setDateTimeRegisterStr(formatTimeStamp(resultSet.getTimestamp("datetimeRegister")));
            timeRegisterCourseModel.setEndTimeRegisterStr(formatTimeStamp(resultSet.getTimestamp("endRegister")));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return timeRegisterCourseModel;
    }
    private String formatTimeStamp(Timestamp timestamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return dateFormat.format(timestamp);
    }
}
