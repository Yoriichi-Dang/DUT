package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.*;
import com.laptrinhjavaWeb.mapper.ClassCourseMapper;
import com.laptrinhjavaWeb.mapper.NotificationTeachMapper;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.NotificationTeachModel;
import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.service.IRegisterRoomService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotificationTeachDAO extends AbstractDAO<NotificationTeachModel> implements INotificationTeachDAO {
    @Inject
    private IClassCourseDAO classCourseDAO;
    @Inject
    private IRegisterRoomDAO registerRoomDAO;
    @Override
    public List<NotificationTeachModel> findAll() {
        String sql="select * from notificationteach order by notificationteach.date desc;";
        List<NotificationTeachModel>list= query(sql,new NotificationTeachMapper());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate;
        for(int i=0;i<list.size();i++){
            formattedDate = list.get(i).getDate().format(dateTimeFormatter);
            list.get(i).setDateStr(formattedDate);
            list.get(i).setClassCourseModel(classCourseDAO.findByCode(list.get(i).getClassCourseCode()));
            if(list.get(i).getContent().equals("học bù")){
                RegisterRoomModel registerRoomModel=new RegisterRoomModel();
                registerRoomModel.setDate(list.get(i).getDate());
                registerRoomModel.setClassCourseCode(list.get(i).getClassCourseCode());
                registerRoomModel=registerRoomDAO.findByModel(registerRoomModel);
                ClassCourseModel classCourseModel=registerRoomModel.getList().get(0).getClassCourseModel();
                String[]lessons=registerRoomModel.getList().get(0).getLessons();
                if(lessons!=null){
                    classCourseModel.setStartLesson(Integer.parseInt(lessons[0]));
                    classCourseModel.setEndLesson(Integer.parseInt(lessons[lessons.length-1]));
                    System.out.println(i);
                }
                classCourseModel.setTeacherModel(list.get(i).getClassCourseModel().getTeacherModel());
                list.get(i).setClassCourseModel(classCourseModel);
            }
        }
        return list;
    }

    @Override
    public NotificationTeachModel findByModel(String ClassCourseCode, LocalDate date) {
       String sql="select * from notificationteach where ClassCourseCode=? and date=?;";
       List<NotificationTeachModel>list=query(sql,new NotificationTeachMapper(),ClassCourseCode,java.sql.Date.valueOf(date));
       if(list==null||list.isEmpty())return null;
       return list.get(0);
    }
}
