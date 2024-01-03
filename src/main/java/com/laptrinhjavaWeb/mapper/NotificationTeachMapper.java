package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.NotificationTeachModel;
import com.laptrinhjavaWeb.model.RoomModel;
import com.laptrinhjavaWeb.model.TeacherModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationTeachMapper implements RowMapper<NotificationTeachModel> {

    @Override
    public NotificationTeachModel mapRow(ResultSet resultSet) {
        NotificationTeachModel notificationTeachModel=new NotificationTeachModel();
        try{
            ClassCourseModel classCourseModel=new ClassCourseModel();
            notificationTeachModel.setId(resultSet.getLong("id"));
            notificationTeachModel.setDate(resultSet.getDate("date").toLocalDate());
            notificationTeachModel.setContent(resultSet.getString("content"));
            notificationTeachModel.setClassCourseCode(resultSet.getString("ClassCourseCode"));
            notificationTeachModel.setClassCourseModel(classCourseModel);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return notificationTeachModel;
    }
}
