package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.RoomModel;
import com.laptrinhjavaWeb.model.TeacherModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassCourseMapper implements RowMapper<ClassCourseModel> {
    @Override
    public ClassCourseModel mapRow(ResultSet resultSet) {
        ClassCourseModel classCourseModel=new ClassCourseModel();
       try{
           classCourseModel.setId(resultSet.getLong("id"));
           classCourseModel.setCourseCode(resultSet.getString("CourseCode"));
            classCourseModel.setCode(resultSet.getString("code"));
            classCourseModel.setDayOnWeek(resultSet.getInt("dayOnWeek"));
            classCourseModel.setStartLesson(resultSet.getInt("startLesson"));
            classCourseModel.setEndLesson(resultSet.getInt("endLesson"));
            classCourseModel.setStartWeek(resultSet.getInt("startWeek"));
            classCourseModel.setEndWeek(resultSet.getInt("endWeek"));
            classCourseModel.setSlotRoom(resultSet.getInt("slotRoom"));
           TeacherModel teacherModel=new TeacherModel();
           teacherModel.setId(resultSet.getLong("TeacherId"));
           teacherModel.setCode(resultSet.getString("TeacherCode"));
           teacherModel.setName(resultSet.getString("TeacherName"));
           RoomModel roomModel=new RoomModel();
           roomModel.setId(resultSet.getLong("RoomId"));
           roomModel.setAreaCode(resultSet.getString("RoomArea"));
           roomModel.setRoomCode(resultSet.getString("RoomCode"));
           roomModel.setSeat(resultSet.getInt("RoomSeat"));
           classCourseModel.setRoomModel(roomModel);
           classCourseModel.setTeacherModel(teacherModel);
           try{
               CourseModel courseModel=new CourseModel();
               courseModel.setId(resultSet.getLong("CourseId"));
               courseModel.setCode(resultSet.getString("CourseCode"));
               courseModel.setName(resultSet.getString("name"));
               courseModel.setNumberCredit(resultSet.getDouble("numberCredit"));
               classCourseModel.setCourseModel(courseModel);
           }catch (Exception e){

           }
           try{
            classCourseModel.setStudentCode(resultSet.getString("StudentCode"));
           }catch (Exception e){

           }
       }catch (SQLException e){

       }
       return classCourseModel;
    }
}
