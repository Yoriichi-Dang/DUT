package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.EducationModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.StudentModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<StudentModel> {
    @Override
    public StudentModel mapRow(ResultSet resultSet) {
        StudentModel studentModel=new StudentModel();
        try{
            studentModel.setId(resultSet.getLong("id"));
            studentModel.setCode(resultSet.getString("code"));
            studentModel.setName(resultSet.getString("fullname"));
            studentModel.setSex(resultSet.getString("sex"));
            studentModel.setBirthday(resultSet.getDate("birthday"));
            studentModel.setAddress(resultSet.getString("address"));
            studentModel.setDistrict(resultSet.getString("district"));
            studentModel.setCity(resultSet.getString("city"));
            studentModel.setPhone(resultSet.getString("phone"));
            studentModel.setLinkFacebook(resultSet.getString("linkFace"));
            studentModel.setEmail(resultSet.getString("email"));
            studentModel.setImage(resultSet.getString("image"));
            studentModel.setCccd(resultSet.getString("cccd"));
            studentModel.setEnrollmentYear(resultSet.getInt("EnrollmentYear"));
            String ClassCode=resultSet.getString("ClassCode");

            if(ClassCode!=null){
                studentModel.setClassCode(ClassCode);
            }
//            if(resultSet.getString("AcademicName")!=null&&resultSet.getString("AcademicCode")!=null){
//                AcademicModel academicModel=new AcademicModel();
//                academicModel.setCode(resultSet.getString("AcademicCode"));
//                academicModel.setName(resultSet.getString("AcademicName"));
//                studentModel.setAcademicModel(academicModel);
//            }
            if(resultSet.getString("YearStudy")!=null){
                studentModel.setYearStudy(resultSet.getString("YearStudy"));
            }
            if(resultSet.getString("AcademicCode")!=null){
                AcademicModel academicModel=new AcademicModel();
              academicModel.setCode(resultSet.getString("AcademicCode"));
              academicModel.setName(resultSet.getString("AcademicName"));
              studentModel.setAcademicModel(academicModel);
            }
            studentModel.setCreateBy(resultSet.getString("createBy"));
            studentModel.setCreateDate(resultSet.getTimestamp("createDate"));

            if(resultSet.getTimestamp("modifiedDate")!=null){
                studentModel.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
            }
            if(resultSet.getString("modifiedBy")!=null){
                studentModel.setModifiedBy(resultSet.getString("modifiedBy"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return studentModel;
    }
}
