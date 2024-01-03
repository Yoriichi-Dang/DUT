package com.laptrinhjavaWeb.model;

import java.time.LocalDate;
import java.util.Date;

public class RegisterRoomModel extends AbstractModel<RegisterRoomModel>{
    private Long RoomId;
    private LocalDate date;
    private String[]lessons;
    private String ClassCourseCode;
    private ClassCourseModel classCourseModel;
    private String roomCode;



    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public ClassCourseModel getClassCourseModel() {
        return classCourseModel;
    }

    public void setClassCourseModel(ClassCourseModel classCourseModel) {
        this.classCourseModel = classCourseModel;
    }

    public Long getRoomId() {
        return RoomId;
    }

    public void setRoomId(Long roomId) {
        RoomId = roomId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String[] getLessons() {
        return lessons;
    }

    public void setLessons(String[] lessons) {
        this.lessons = lessons;
    }

    public String getClassCourseCode() {
        return ClassCourseCode;
    }

    public void setClassCourseCode(String classCourseCode) {
        ClassCourseCode = classCourseCode;
    }
}
