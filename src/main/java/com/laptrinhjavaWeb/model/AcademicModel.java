package com.laptrinhjavaWeb.model;

import java.util.List;

public class AcademicModel extends AbstractModel<AcademicModel>{
    private String name;
    //fk
    private String facultyCode;//khóa ngoại
    private List<ClassModel>classModelList;



    public List<ClassModel> getClassModelList() {
        return classModelList;
    }

    public void setClassModelList(List<ClassModel> classModelList) {
        this.classModelList = classModelList;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
