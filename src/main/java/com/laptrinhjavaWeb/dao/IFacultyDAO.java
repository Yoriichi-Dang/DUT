package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.paging.Pageable;

import java.util.List;

public interface IFacultyDAO {
    FacultyModel findByCode(String code);
    Long insert(FacultyModel facultyModel);
    FacultyModel findById(Long id);
    List<FacultyModel> findAll();
    FacultyModel findAllAcademicByCode(String code);
    void update(FacultyModel facultyModel);
    void delete(Long id);

}
