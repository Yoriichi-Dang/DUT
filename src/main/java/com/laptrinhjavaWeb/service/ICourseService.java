package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.CourseModel;

import java.util.List;

public interface ICourseService {
    CourseModel findByCode(String code);
    Long insert(CourseModel courseModel);
    CourseModel findById(Long id);
    List<CourseModel> findAll();
    Long update(CourseModel courseModel);
    void delete(Long id);
    CourseModel findByFacultyCode(String code);
    CourseModel findAllCourseDependent(String courseCode);
    Long insertDependentCourse(CourseModel courseModel);
    Long updateDependentCourse(CourseModel courseModel);
    CourseModel findDepentdentCourseById(Long id);
    Long deleteDepentdentCourse(Long id);
}
