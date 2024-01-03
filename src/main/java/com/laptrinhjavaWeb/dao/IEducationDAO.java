package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.EducationModel;

import java.util.List;

public interface IEducationDAO {
    Long insert(EducationModel educationModel);
    EducationModel findById(Long id);
    EducationModel findByCode(String code);
    EducationModel findByFacultyCode(String code);
    Long insertEducationCourse(EducationModel educationModel);
    EducationModel findAllCourseByCode(String code);
    EducationModel findAllEducationCourseByFacultyCode(EducationModel educationModel);
    EducationModel findStudentEducationByCode(String code);
    EducationModel findAllEducationByStudentCode(String code);
    List<EducationModel> findAll();
    void update(EducationModel educationModel);
    void delete(Long id);
}
