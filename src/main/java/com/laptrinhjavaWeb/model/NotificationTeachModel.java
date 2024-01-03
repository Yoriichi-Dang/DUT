package com.laptrinhjavaWeb.model;

import java.time.LocalDate;

public class NotificationTeachModel extends AbstractModel<NotificationTeachModel>{
    private String ClassCourseCode;
    private LocalDate date;
    private String content;
    private ClassCourseModel classCourseModel;
    private String dateStr;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getClassCourseCode() {
        return ClassCourseCode;
    }

    public void setClassCourseCode(String classCourseCode) {
        ClassCourseCode = classCourseCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ClassCourseModel getClassCourseModel() {
        return classCourseModel;
    }

    public void setClassCourseModel(ClassCourseModel classCourseModel) {
        this.classCourseModel = classCourseModel;
    }

}
