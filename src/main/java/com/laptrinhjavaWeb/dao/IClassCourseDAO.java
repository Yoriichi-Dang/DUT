package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.ClassCourseModel;

import java.util.List;

public interface IClassCourseDAO {
    Long insert(ClassCourseModel classCourseModel);
    Long insertHistoryClassCourse(ClassCourseModel classCourseModel);
    ClassCourseModel findAllRegisterClassCourseByStudentCode(String studentCode);
    ClassCourseModel findClassCourseStudied(ClassCourseModel classCourseModel);
    ClassCourseModel findAllStudentInClassCouse(String classcourseCode);
    ClassCourseModel findById(Long id);
    ClassCourseModel findByCode(String code);
    ClassCourseModel findAllByCourseCode(String code);
    List<ClassCourseModel> findAll();
    Long update(ClassCourseModel classCourseModel);
    void delete(Long id);
    Long deleteHistoryRegisterClassCourse(ClassCourseModel classCourseModel);
    ClassCourseModel findAllByTeacherCode(String teacherCode);
}
