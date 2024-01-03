package com.laptrinhjavaWeb.model;

import java.util.List;

public class CourseDependentModel extends  AbstractModel<CourseDependentModel>{
    private CourseModel courseModel;
    private String[] DependentList_courseStudyBeforeCodeStr;
    private String[] DependentList_courseStudyTogetherCodeStr;
    private List<CourseModel> DependentList_courseStudyBeforeCode;
    private List<CourseModel> DependentList_courseStudyTogetherCode;

    public CourseModel getCourseModel() {
        return courseModel;
    }

    public void setCourseModel(CourseModel courseModel) {
        this.courseModel = courseModel;
    }

    public String[] getDependentList_courseStudyBeforeCodeStr() {
        return DependentList_courseStudyBeforeCodeStr;
    }

    public void setDependentList_courseStudyBeforeCodeStr(String[] dependentList_courseStudyBeforeCodeStr) {
        DependentList_courseStudyBeforeCodeStr = dependentList_courseStudyBeforeCodeStr;
    }

    public String[] getDependentList_courseStudyTogetherCodeStr() {
        return DependentList_courseStudyTogetherCodeStr;
    }

    public void setDependentList_courseStudyTogetherCodeStr(String[] dependentList_courseStudyTogetherCodeStr) {
        DependentList_courseStudyTogetherCodeStr = dependentList_courseStudyTogetherCodeStr;
    }

    public List<CourseModel> getDependentList_courseStudyBeforeCode() {
        return DependentList_courseStudyBeforeCode;
    }

    public void setDependentList_courseStudyBeforeCode(List<CourseModel> dependentList_courseStudyBeforeCode) {
        DependentList_courseStudyBeforeCode = dependentList_courseStudyBeforeCode;
    }

    public List<CourseModel> getDependentList_courseStudyTogetherCode() {
        return DependentList_courseStudyTogetherCode;
    }

    public void setDependentList_courseStudyTogetherCode(List<CourseModel> dependentList_courseStudyTogetherCode) {
        DependentList_courseStudyTogetherCode = dependentList_courseStudyTogetherCode;
    }
}
