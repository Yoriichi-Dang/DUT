package com.laptrinhjavaWeb.mapper;


import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.EducationModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationMapper implements RowMapper<EducationModel> {
    @Override
    public EducationModel mapRow(ResultSet resultSet) {
        EducationModel educationModel=new EducationModel();
        try{
            educationModel.setId(resultSet.getLong("id"));
            educationModel.setCode(resultSet.getString("code"));
            educationModel.setName(resultSet.getString("name"));
            educationModel.setSemesterStudy(resultSet.getInt("semesterStudy"));
//            educationModel.setCreditRequired(resultSet.getInt("creditRequired"));
//            educationModel.setCreditObligatory(resultSet.getInt("creditObligatory"));
//            educationModel.setCreditElective(resultSet.getInt("creditElective"));
            educationModel.setTypeEducation(resultSet.getString("typeEducation"));
            AcademicModel academicModel=new AcademicModel();
            academicModel.setId(resultSet.getLong("AcademicId"));
            academicModel.setCode(resultSet.getString("AcademicCode"));
            academicModel.setName(resultSet.getString("AcademicName"));
            if(academicModel!=null)educationModel.setAcademicModel(academicModel);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return educationModel;
    }
}
