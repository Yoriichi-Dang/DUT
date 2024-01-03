package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.CourseDependentModel;
import com.laptrinhjavaWeb.model.CourseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDependentMapper implements RowMapper<CourseDependentModel> {

    @Override
    public CourseDependentModel mapRow(ResultSet resultSet) {
        CourseDependentModel courseDependentModel=new CourseDependentModel();
        try{
            CourseModel courseModel=new CourseModel();
            courseModel.setId(resultSet.getLong("id"));
            courseModel.setCode(resultSet.getString("CourseCode"));
            courseModel.setNumberCredit(resultSet.getDouble("numberCredit"));
            courseModel.setPointStudy(resultSet.getDouble("pointStudy"));
            courseModel.setName(resultSet.getString("CourseName"));
            String DependentList_courseStudyBeforeCode=resultSet.getString("DependentList_courseStudyBefore");
            String DependentList_courseStudyTogether=resultSet.getString("DependentList_courseStudyTogether");
            if(DependentList_courseStudyBeforeCode!=null) courseDependentModel.setDependentList_courseStudyBeforeCodeStr(DependentList_courseStudyBeforeCode.split(","));
            if(DependentList_courseStudyTogether!=null)courseDependentModel.setDependentList_courseStudyTogetherCodeStr(DependentList_courseStudyTogether.split(","));
            courseDependentModel.setCourseModel(courseModel);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return courseDependentModel;
    }
}
