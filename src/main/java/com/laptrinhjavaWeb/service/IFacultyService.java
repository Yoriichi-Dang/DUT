package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;

import java.util.List;

public interface IFacultyService {
    FacultyModel findByCode(String code);
    Long insert(FacultyModel facultyModel);
    FacultyModel findById(Long id);
    List<FacultyModel> findAll();
    FacultyModel findAllAcademicByCode(String code);
    FacultyModel update(FacultyModel facultyModel);
    void delete(Long id);

}
