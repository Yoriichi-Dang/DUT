package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.ClassModel;

import java.util.List;

public interface IClassService {
    Long insert(ClassModel classModel);
    ClassModel findClasById(Long id);
    ClassModel findClassCode(String code);

    List<ClassModel> findAll();
    ClassModel update(ClassModel classModel);
    void delete(Long id);
    ClassModel findAllStudentByClassCode(String code);
}
