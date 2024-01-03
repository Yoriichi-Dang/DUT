package com.laptrinhjavaWeb.model;

import java.util.List;

public class EducationModel extends AbstractModel<EducationModel> {
    private String name;
    private int semesterStudy;
    private int creditRequired;
    private int creditObligatory;
    private int creditElective;
    private String typeEducation;
    private String academicCode;
    private String facultyCode;
    private AcademicModel academicModel;
    private List<CourseModel>courseModelList;
    private List<CourseDependentModel>courseDependentModelList;
    private List<String>listCourseCode;

    public List<CourseDependentModel> getCourseDependentModelList() {
        return courseDependentModelList;
    }

    public void setCourseDependentModelList(List<CourseDependentModel> courseDependentModelList) {
        this.courseDependentModelList = courseDependentModelList;
    }

    public List<String> getListCourseCode() {
        return listCourseCode;
    }

    public void setListCourseCode(List<String> listCourseCode) {
        this.listCourseCode = listCourseCode;
    }

    public List<CourseModel> getCourseModelList() {
        return courseModelList;
    }

    public void setCourseModelList(List<CourseModel> courseModelList) {
        this.courseModelList = courseModelList;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }


    public AcademicModel getAcademicModel() {
        return academicModel;
    }

    public void setAcademicModel(AcademicModel academicModel) {
        this.academicModel = academicModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemesterStudy() {
        return semesterStudy;
    }

    public void setSemesterStudy(int semesterStudy) {
        this.semesterStudy = semesterStudy;
    }

    public int getCreditRequired() {
        return creditRequired;
    }

    public void setCreditRequired(int creditRequired) {
        this.creditRequired = creditRequired;
    }

    public int getCreditObligatory() {
        return creditObligatory;
    }

    public void setCreditObligatory(int creditObligatory) {
        this.creditObligatory = creditObligatory;
    }

    public int getCreditElective() {
        return creditElective;
    }

    public void setCreditElective(int creditElective) {
        this.creditElective = creditElective;
    }

    public String getTypeEducation() {
        return typeEducation;
    }

    public void setTypeEducation(String typeEducation) {
        this.typeEducation = typeEducation;
    }

    public String getAcademicCode() {
        return academicCode;
    }

    public void setAcademicCode(String academicCode) {
        this.academicCode = academicCode;
    }
}
