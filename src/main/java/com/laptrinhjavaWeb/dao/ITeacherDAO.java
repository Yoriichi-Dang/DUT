package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.model.TeacherModel;

import java.util.List;

public interface ITeacherDAO {
    Long insert(TeacherModel teacherModel);
    TeacherModel findById(Long id);
    TeacherModel findByCode(String code);

    List<TeacherModel> findAll();
    Long update(TeacherModel teacherModel,boolean updateCode);
    void delete(Long id);
}
