package com.laptrinhjavaWeb.model;

import java.util.Date;

public class ClassCourseModel extends AbstractModel<ClassCourseModel> {
    private String courseCode;
    private String teacherCode;
    private String areaCode;
    private String numberRoomCode;
    private String roomCode;
    private int dayOnWeek;
    private int startLesson;
    private int endLesson;
    private int startWeek;
    private int endWeek;
    private int slotRoom;
    private int numberCreate;
    private int enrollYear;
    private Date date;
    private CourseModel courseModel;
    private TeacherModel teacherModel;//get id in listclasscourseteacher
    private RoomModel roomModel;// get id in listclasscourseroom
    private StudentModel studentModel;
    private String studentCode;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumberRoomCode() {
        return numberRoomCode;
    }

    public void setNumberRoomCode(String numberRoomCode) {
        this.numberRoomCode = numberRoomCode;
    }

    public int getDayOnWeek() {
        return dayOnWeek;
    }

    public void setDayOnWeek(int dayOnWeek) {
        this.dayOnWeek = dayOnWeek;
    }

    public int getStartLesson() {
        return startLesson;
    }

    public void setStartLesson(int startLesson) {
        this.startLesson = startLesson;
    }

    public int getEndLesson() {
        return endLesson;
    }

    public void setEndLesson(int endLesson) {
        this.endLesson = endLesson;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getSlotRoom() {
        return slotRoom;
    }

    public void setSlotRoom(int slotRoom) {
        this.slotRoom = slotRoom;
    }

    public int getNumberCreate() {
        return numberCreate;
    }

    public void setNumberCreate(int numberCreate) {
        this.numberCreate = numberCreate;
    }

    public int getEnrollYear() {
        return enrollYear;
    }

    public void setEnrollYear(int enrollYear) {
        this.enrollYear = enrollYear;
    }

    public CourseModel getCourseModel() {
        return courseModel;
    }

    public void setCourseModel(CourseModel courseModel) {
        this.courseModel = courseModel;
    }

    public TeacherModel getTeacherModel() {
        return teacherModel;
    }

    public void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }
}
