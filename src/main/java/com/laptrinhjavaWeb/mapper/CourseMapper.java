package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.FacultyModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<CourseModel>{
    @Override
    public CourseModel mapRow(ResultSet resultSet) {
       CourseModel courseModel=new CourseModel();
        try{
            courseModel.setId(resultSet.getLong("id"));
            courseModel.setCode(resultSet.getString("code"));
            courseModel.setName(resultSet.getString("name"));
            try{
                courseModel.setNumberCredit(resultSet.getDouble("numberCredit"));
                courseModel.setPointStudy(resultSet.getDouble("pointStudy"));
                courseModel.setPointBT(resultSet.getDouble("BT"));
                courseModel.setPointBV(resultSet.getDouble("BV"));
                courseModel.setPointCK(resultSet.getDouble("CK"));
                courseModel.setPointDA(resultSet.getDouble("DA"));
                courseModel.setPointGK(resultSet.getDouble("GK"));
                courseModel.setPointQT(resultSet.getDouble("QT"));
                courseModel.setPointTH(resultSet.getDouble("TH"));
                FacultyModel facultyModel=new FacultyModel();
                facultyModel.setId(resultSet.getLong("FacultyId"));
                facultyModel.setCode(resultSet.getString("FacultyCode"));
                facultyModel.setName(resultSet.getString("FacultyName"));
                courseModel.setFacultyModel(facultyModel);
            }catch (Exception e){
            }
//
            try{
                courseModel.setTypeCourse(resultSet.getString("type"));
            }catch (Exception e){

            }
//            userModel.setCreateBy(resultSet.getString("createBy"));
//            userModel.setCreateDate(resultSet.getTimestamp("createDate"));

//            if(resultSet.getTimestamp("modifiesDate")!=null){
//                userModel.setModifiedDate(resultSet.getTimestamp("modifiesDate"));
//            }
//            if(resultSet.getString("modifiesBy")!=null){
//                userModel.setModifiedBy(resultSet.getString("modifiesBy"));
//            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return courseModel;
    }
}
