package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.INotificationTeachDAO;
import com.laptrinhjavaWeb.dao.IRegisterRoomDAO;
import com.laptrinhjavaWeb.dao.IRoomDAO;
import com.laptrinhjavaWeb.mapper.RegisterRoomMapper;
import com.laptrinhjavaWeb.model.NotificationTeachModel;
import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.model.RoomModel;

import javax.inject.Inject;
import java.util.List;

public class RegisterRoomDAO extends AbstractDAO<RegisterRoomModel> implements IRegisterRoomDAO {
    @Inject
    private IRoomDAO roomDAO;

    @Override
    public RegisterRoomModel findAllByClassCourseCode(String classCourseCode) {
        String sql="select * from registerroom\n" +
                "where registerroom.ClassCourseCode=?;";
        RegisterRoomModel registerRoomModel=new RegisterRoomModel();
        List<RegisterRoomModel>list=query(sql,new RegisterRoomMapper(),classCourseCode);
        if(list!=null&&!list.isEmpty()){
            registerRoomModel.setList(list);
        }
        registerRoomModel.setClassCourseCode(classCourseCode);
        return registerRoomModel;
    }

    @Override
    public RegisterRoomModel findScheduleTeach(String teacherCode, RegisterRoomModel registerRoomModel) {
        String sql="SELECT\n" +
                "    ClassCourseCode,\n" +
                "    CourseName,\n" +
                "    Area,\n" +
                "    Code,\n" +
                "    GROUP_CONCAT(lesson ORDER BY lesson ASC) AS Lessons\n" +
                "FROM\n" +
                "    (\n" +
                "        SELECT\n" +
                "            registerroom.*,\n" +
                "            course.name AS CourseName,\n" +
                "            room.Area AS Area,\n" +
                "            room.Code AS Code\n" +
                "        FROM\n" +
                "            listteacherclasscourse\n" +
                "            INNER JOIN registerroom ON listteacherclasscourse.ClassCourseCode = registerroom.ClassCourseCode\n" +
                "            INNER JOIN room ON registerroom.RoomId = room.id\n" +
                "            INNER JOIN listclasscourse ON registerroom.ClassCourseCode = listclasscourse.ClassCourseCode\n" +
                "            INNER JOIN course ON listclasscourse.CourseCode = course.code\n" +
                "        WHERE\n" +
                "            registerroom.date = ?\n" +
                "            AND listteacherclasscourse.TeacherCode = ?\n" +
                "    ) AS Subquery\n" +
                "GROUP BY\n" +
                "    ClassCourseCode, CourseName, Area, Code;\n";
        List<RegisterRoomModel>list=query(sql,new RegisterRoomMapper(),java.sql.Date.valueOf(registerRoomModel.getDate()),teacherCode);
        if(!list.isEmpty()){
            registerRoomModel.setList(list);
            return registerRoomModel;
        }else{
            return null;
        }
    }

    @Override
    public Long deleteClassCourseTeachInDate(RegisterRoomModel registerRoomModel) {
        String sql="delete from registerroom where date=? and ClassCourseCode=?";
        Long id=update(sql,java.sql.Date.valueOf(registerRoomModel.getDate()),registerRoomModel.getClassCourseCode());
        //insert notification Teach
        if(id!=-1L){

            if(id!=-1L){
                sql="insert notificationteach(ClassCourseCode,date,content) values(?,?,?)";
                String message="nghỉ học";
                id=insert(sql,registerRoomModel.getClassCourseCode(),java.sql.Date.valueOf(registerRoomModel.getDate()),message);
                if(id==-1L){
                    sql="update notificationteach set content=? where ClassCourseCode=? and date=?";
                    id=update(sql,message,registerRoomModel.getClassCourseCode(),java.sql.Date.valueOf(registerRoomModel.getDate()));
                }
            }
        }
        return id;
    }

    @Override
    public Long registerClassCourseTeachInDate(RegisterRoomModel registerRoomModel) {
       String  sql="insert registerroom(RoomId,date,lesson,ClassCourseCode) values(?,?,?,?);";
        Long id=-1L;
        for(int i=registerRoomModel.getClassCourseModel().getStartLesson();i<registerRoomModel.getClassCourseModel().getEndLesson();i++){
                try{
                    if(insert(sql,registerRoomModel.getClassCourseModel().getRoomModel().getId(),java.sql.Date.valueOf(registerRoomModel.getDate()),i,registerRoomModel.getClassCourseCode())==-1L)return -2L;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        sql="insert notificationteach(ClassCourseCode,date,content) values(?,?,?)";
        String message="học bù";
        id=insert(sql,registerRoomModel.getClassCourseCode(),java.sql.Date.valueOf(registerRoomModel.getDate()),message);
        return id;
    }

    @Override
    public RegisterRoomModel findByModel(RegisterRoomModel registerRoomModel) {
        // đầu vào là date và classcourscode
        String sql="SELECT\n" +
                "    ClassCourseCode,\n" +
                "    CourseName,\n" +
                "    Area,\n" +
                "    Code,\n" +
                "    GROUP_CONCAT(lesson ORDER BY lesson ASC) AS Lessons\n" +
                "FROM\n" +
                "    (\n" +
                "        SELECT\n" +
                "            registerroom.*,\n" +
                "            course.name AS CourseName,\n" +
                "            room.Area AS Area,\n" +
                "            room.Code AS Code\n" +
                "        FROM\n" +
                "            listteacherclasscourse\n" +
                "            INNER JOIN registerroom ON listteacherclasscourse.ClassCourseCode = registerroom.ClassCourseCode\n" +
                "            INNER JOIN room ON registerroom.RoomId = room.id\n" +
                "            INNER JOIN listclasscourse ON registerroom.ClassCourseCode = listclasscourse.ClassCourseCode\n" +
                "            INNER JOIN course ON listclasscourse.CourseCode = course.code\n" +
                "        WHERE\n" +
                "            registerroom.date = ?\n" +
                "            AND registerroom.ClassCourseCode = ?\n" +
                "    ) AS Subquery\n" +
                "GROUP BY\n" +
                "    ClassCourseCode, CourseName, Area, Code;\n";
        List<RegisterRoomModel>list=query(sql,new RegisterRoomMapper(),java.sql.Date.valueOf(registerRoomModel.getDate()),registerRoomModel.getClassCourseCode());
        if(list!=null&&!list.isEmpty()){
            registerRoomModel.setList(list);
        }
        return registerRoomModel;
    }

    @Override
    public RoomModel findByRoomByDate(RegisterRoomModel registerRoomModel) {
        String sql="SELECT\n" +
                "    ClassCourseCode,\n" +
                "    CourseName,\n" +
                "    Area,\n" +
                "    Code,\n" +
                "    GROUP_CONCAT(lesson ORDER BY lesson ASC) AS Lessons,\n" +
                "    seat\n" +
                "FROM\n" +
                "    (\n" +
                "        SELECT\n" +
                "            registerroom.*,\n" +
                "            course.name AS CourseName,\n" +
                "            room.Area AS Area,\n" +
                "            room.Code AS Code,\n" +
                "            room.seat\n" +
                "        FROM\n" +
                "            listteacherclasscourse\n" +
                "            INNER JOIN registerroom ON listteacherclasscourse.ClassCourseCode = registerroom.ClassCourseCode\n" +
                "            INNER JOIN room ON registerroom.RoomId = room.id\n" +
                "            INNER JOIN listclasscourse ON registerroom.ClassCourseCode = listclasscourse.ClassCourseCode\n" +
                "            INNER JOIN course ON listclasscourse.CourseCode = course.code\n" +
                "        WHERE\n" +
                "            registerroom.date = ? \n" +
                "            AND lesson BETWEEN ? AND ?\n" +
                "    ) AS Subquery\n" +
                "GROUP BY\n" +
                "    ClassCourseCode, CourseName, Area, Code;\n";
        List<RegisterRoomModel>list=query(sql,new RegisterRoomMapper(),java.sql.Date.valueOf(registerRoomModel.getDate()),registerRoomModel.getClassCourseModel().getStartLesson(),registerRoomModel.getClassCourseModel().getEndLesson());
        RoomModel roomModel=new RoomModel();
        List<RoomModel>roomModelList=roomDAO.findAll();
        if(list!=null&&!list.isEmpty()){
           for(int i=0;i<list.size();i++){
               for(int j=0;j<roomModelList.size();j++){
                   if(list.get(i).getClassCourseModel().getRoomModel().getAreaCode().equals(roomModelList.get(j).getAreaCode())&&list.get(i).getClassCourseModel().getRoomModel().getRoomCode().equals(roomModelList.get(j).getRoomCode())){
                       roomModelList.remove(j);
                       j--;
                       break;
                   }
               }
           }
        }
        roomModel.setList(roomModelList);
        return roomModel;
    }
}
