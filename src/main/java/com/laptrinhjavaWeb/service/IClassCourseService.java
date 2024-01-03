package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.ClassModel;

import java.util.List;

public interface IClassCourseService {
    Long insert(ClassCourseModel classCourseModel);
    ClassCourseModel findById(Long id);
    ClassCourseModel findByCode(String code);
    List<ClassCourseModel> findAll();
    Long update(ClassCourseModel classCourseModel);
    void delete(Long id);
    ClassCourseModel findAllByCourseCode(String code);
    Long insertHistoryClassCourse(ClassCourseModel classCourseModel);
    ClassCourseModel findAllRegisterClassCourseByStudentCode(String studentCode);
    ClassCourseModel findClassCourseStudied(ClassCourseModel classCourseModel);

    ClassCourseModel findAllStudentInClassCouse(String classcourseCode);
    Long deleteHistoryRegisterClassCourse(ClassCourseModel classCourseModel);
    ClassCourseModel findAllByTeacherCode(String teacherCode);
}
