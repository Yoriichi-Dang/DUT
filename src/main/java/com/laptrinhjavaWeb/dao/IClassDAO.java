package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.FacultyModel;

import java.util.List;

public interface IClassDAO {
    ClassModel findByCode(String code);
    Long insert(ClassModel classModel);
    ClassModel findById(Long id);
    List<ClassModel> findAll();
    void update(ClassModel classModel);
    void delete(Long id);
    ClassModel findAllStudentByClassCode(String code);
}
