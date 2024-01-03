package com.laptrinhjavaWeb.model;

import java.util.List;

public class FacultyModel extends AbstractModel<FacultyModel>{
    private String name;
    private List<AcademicModel> academicModelList;


    public List<AcademicModel> getAcademicModelList() {
        return academicModelList;
    }

    public void setAcademicModelList(List<AcademicModel> academicModelList) {
        this.academicModelList = academicModelList;
    }

    public FacultyModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
