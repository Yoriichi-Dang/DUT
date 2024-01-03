package com.laptrinhjavaWeb.model;

public class ClassCourseStudentModel extends AbstractModel<ClassCourseModel>{
    private String ClassCourseCode;
    private ClassCourseModel classCourseModel;
    private String StudentCode;
    private StudentModel studentModel;

    public String getClassCourseCode() {
        return ClassCourseCode;
    }

    public void setClassCourseCode(String classCourseCode) {
        ClassCourseCode = classCourseCode;
    }

    public ClassCourseModel getClassCourseModel() {
        return classCourseModel;
    }

    public void setClassCourseModel(ClassCourseModel classCourseModel) {
        this.classCourseModel = classCourseModel;
    }

    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String studentCode) {
        StudentCode = studentCode;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }
}
