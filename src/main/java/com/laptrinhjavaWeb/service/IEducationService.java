package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.EducationModel;

import java.util.List;

public interface IEducationService {
    Long insert(EducationModel educationModel);
    EducationModel findById(Long id);
    EducationModel findByCode(String code);
    EducationModel findByFacultyCode(String code);
    List<EducationModel> findAll();
    Long insertEducationCourse(EducationModel educationModel);
    EducationModel findAllCourseByCode(String code);
    EducationModel findAllEducationCourseByFacultyCode(EducationModel educationModel);
    EducationModel findAllEducationByStudentCode(String code);
    void update(EducationModel educationModel);
    void delete(Long id);
}
