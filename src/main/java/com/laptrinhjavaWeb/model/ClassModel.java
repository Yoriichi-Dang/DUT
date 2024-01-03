package com.laptrinhjavaWeb.model;

import java.util.List;

public class ClassModel extends AbstractModel<ClassModel> {
    private String academicCode;
    private List<StudentModel>studentModelList;

    public String getAcademicCode() {
        return academicCode;
    }

    public void setAcademicCode(String academicCode) {
        this.academicCode = academicCode;
    }

    public List<StudentModel> getStudentModelList() {
        return studentModelList;
    }

    public void setStudentModelList(List<StudentModel> studentModelList) {
        this.studentModelList = studentModelList;
    }
}
