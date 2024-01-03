package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;

import java.util.List;

public interface IAcademicDAO {
    Long insert(AcademicModel academicModel);
    AcademicModel findById(Long id);
    AcademicModel findByCode(String code);
    AcademicModel findAllClassByCode(String code);

    List<AcademicModel> findAll();
    void update(AcademicModel academicModel);
    void delete(Long id);
}
