package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.model.RoomModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRoomMapper implements RowMapper<RegisterRoomModel> {

    @Override
    public RegisterRoomModel mapRow(ResultSet resultSet) {
        RegisterRoomModel registerRoomModel=new RegisterRoomModel();
        try{
            try{
                String lessons=resultSet.getString("Lessons");
                if(lessons!=null) registerRoomModel.setLessons(lessons.split(","));
                try{
                    registerRoomModel.setId(resultSet.getLong("id"));
                    registerRoomModel.setRoomId(resultSet.getLong("RoomId"));
                    registerRoomModel.setDate(resultSet.getDate("date").toLocalDate());
                }catch (Exception e){

                }
                registerRoomModel.setClassCourseCode(resultSet.getString("ClassCourseCode"));
            }catch (Exception e){

            }
            try{
                ClassCourseModel classCourseModel=new ClassCourseModel();
                classCourseModel.setCode(resultSet.getString("ClassCourseCode"));
                CourseModel courseModel=new CourseModel();
                courseModel.setName(resultSet.getString("CourseName"));
                classCourseModel.setCourseModel(courseModel);
                RoomModel roomModel=new RoomModel();
                roomModel.setRoomCode(resultSet.getString("Code"));
                roomModel.setAreaCode(resultSet.getString("Area"));
                try{
                    roomModel.setSeat(resultSet.getInt("seat"));
                }catch (Exception e){

                }
                classCourseModel.setRoomModel(roomModel);
                registerRoomModel.setClassCourseModel(classCourseModel);
            }catch (Exception e){

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return registerRoomModel;
    }
}
