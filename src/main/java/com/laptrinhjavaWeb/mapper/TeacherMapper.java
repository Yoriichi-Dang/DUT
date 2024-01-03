package com.laptrinhjavaWeb.mapper;


import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.TeacherModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<TeacherModel> {
    @Override
    public TeacherModel mapRow(ResultSet resultSet) {
        TeacherModel teacherModel=new TeacherModel();
        try{
            teacherModel.setId(resultSet.getLong("id"));
            teacherModel.setCode(resultSet.getString("code"));
            teacherModel.setName(resultSet.getString("fullname"));
            teacherModel.setSex(resultSet.getString("sex"));
            teacherModel.setBirthday(resultSet.getDate("birthday"));
            teacherModel.setAddress(resultSet.getString("address"));
            teacherModel.setDistrict(resultSet.getString("district"));
            teacherModel.setCity(resultSet.getString("city"));
            teacherModel.setHometown(resultSet.getString("homeTown"));
            teacherModel.setLevel(resultSet.getString("level"));
            teacherModel.setSpecialized(resultSet.getString("specialized"));
            teacherModel.setPhone(resultSet.getString("phone"));
            teacherModel.setEmail(resultSet.getString("email"));
            teacherModel.setImage(resultSet.getString("image"));
            teacherModel.setCccd(resultSet.getString("cccd"));
            FacultyModel facultyModel=new FacultyModel();
            facultyModel.setId(resultSet.getLong("FacultyId"));
            facultyModel.setCode(resultSet.getString("FacultyCode"));
            facultyModel.setName(resultSet.getString("FacultyName"));
            if(facultyModel!=null)teacherModel.setFacultyModel(facultyModel);
//            if(resultSet.getString("AcademicName")!=null&&resultSet.getString("AcademicCode")!=null){
//                AcademicModel academicModel=new AcademicModel();
//                academicModel.setCode(resultSet.getString("AcademicCode"));
//                academicModel.setName(resultSet.getString("AcademicName"));
//                studentModel.setAcademicModel(academicModel);
//            }

            teacherModel.setCreateBy(resultSet.getString("createBy"));
            teacherModel.setCreateDate(resultSet.getTimestamp("createDate"));

            if(resultSet.getTimestamp("modifiedDate")!=null){
                teacherModel.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
            }
            if(resultSet.getString("modifiedBy")!=null){
                teacherModel.setModifiedBy(resultSet.getString("modifiedBy"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return teacherModel;
    }
}
