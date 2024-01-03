package com.laptrinhjavaWeb.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class TimeRegisterCourseModel extends AbstractModel<TimeRegisterCourseModel>{
    private String typeRegister;
   private String value;
   private String dateTimeRegisterStr;
   private String endTimeRegisterStr;
   private Timestamp dateTimeRegister;

    private Timestamp endRegister;
    private FacultyModel facultyModel;

    public FacultyModel getFacultyModel() {
        return facultyModel;
    }

    public void setFacultyModel(FacultyModel facultyModel) {
        this.facultyModel = facultyModel;
    }

    public String getDateTimeRegisterStr() {
        return dateTimeRegisterStr;
    }

    public void setDateTimeRegisterStr(String dateTimeRegisterStr) {
        this.dateTimeRegisterStr = dateTimeRegisterStr;
    }

    public String getEndTimeRegisterStr() {
        return endTimeRegisterStr;
    }

    public void setEndTimeRegisterStr(String endTimeRegisterStr) {
        this.endTimeRegisterStr = endTimeRegisterStr;
    }

    public String getTypeRegister() {
        return typeRegister;
    }

    public void setTypeRegister(String typeRegister) {
        this.typeRegister = typeRegister;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getDateTimeRegister() {
        return dateTimeRegister;
    }

    public void setDateTimeRegister(Timestamp dateTimeRegister) {
        this.dateTimeRegister = dateTimeRegister;
    }

    public Timestamp getEndRegister() {
        return endRegister;
    }

    public void setEndRegister(Timestamp endRegister) {
        this.endRegister = endRegister;
    }
}
