package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.TeacherModel;

import java.util.List;

public interface ITeacherService {
    Long insert(TeacherModel teacherModel);
    TeacherModel findById(Long id);
    TeacherModel findByCode(String code);
    List<TeacherModel> findAll();
    TeacherModel update(TeacherModel teacherModel);
    void delete(Long id);
}
