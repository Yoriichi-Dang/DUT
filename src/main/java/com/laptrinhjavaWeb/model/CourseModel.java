package com.laptrinhjavaWeb.model;


import java.util.List;

public class CourseModel extends AbstractModel<CourseModel>{
    private String name;
    private double numberCredit;
    private double pointStudy;
    private CourseModel dependentCourse;
    private String dependentCourseCode;
    private boolean elective;// tự chọn
    private boolean canRegister;// có thể đăng ký
    private double pointBT;
    private double pointBV;
    private double pointCK;
    private double pointDA;

    private double pointGK;

    private double pointQT;

    private double pointTH;
    private String facultyCode;
    private FacultyModel facultyModel;
    private String typeCourse;
    private int semester;

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumberCredit() {
        return numberCredit;
    }

    public void setNumberCredit(double numberCredit) {
        this.numberCredit = numberCredit;
    }

    public double getPointStudy() {
        return pointStudy;
    }

    public void setPointStudy(double pointStudy) {
        this.pointStudy = pointStudy;
    }

    public CourseModel getDependentCourse() {
        return dependentCourse;
    }

    public void setDependentCourse(CourseModel dependentCourse) {
        this.dependentCourse = dependentCourse;
    }

    public String getDependentCourseCode() {
        return dependentCourseCode;
    }

    public void setDependentCourseCode(String dependentCourseCode) {
        this.dependentCourseCode = dependentCourseCode;
    }

    public boolean isElective() {
        return elective;
    }

    public void setElective(boolean elective) {
        this.elective = elective;
    }

    public boolean isCanRegister() {
        return canRegister;
    }

    public void setCanRegister(boolean canRegister) {
        this.canRegister = canRegister;
    }

    public double getPointBT() {
        return pointBT;
    }

    public void setPointBT(double pointBT) {
        this.pointBT = pointBT;
    }

    public double getPointBV() {
        return pointBV;
    }

    public void setPointBV(double pointBV) {
        this.pointBV = pointBV;
    }

    public double getPointCK() {
        return pointCK;
    }

    public void setPointCK(double pointCK) {
        this.pointCK = pointCK;
    }

    public double getPointDA() {
        return pointDA;
    }

    public void setPointDA(double pointDA) {
        this.pointDA = pointDA;
    }

    public double getPointGK() {
        return pointGK;
    }

    public void setPointGK(double pointGK) {
        this.pointGK = pointGK;
    }

    public double getPointQT() {
        return pointQT;
    }

    public void setPointQT(double pointQT) {
        this.pointQT = pointQT;
    }

    public double getPointTH() {
        return pointTH;
    }

    public void setPointTH(double pointTH) {
        this.pointTH = pointTH;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public FacultyModel getFacultyModel() {
        return facultyModel;
    }

    public void setFacultyModel(FacultyModel facultyModel) {
        this.facultyModel = facultyModel;
    }

    public String getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(String typeCourse) {
        this.typeCourse = typeCourse;
    }
}
