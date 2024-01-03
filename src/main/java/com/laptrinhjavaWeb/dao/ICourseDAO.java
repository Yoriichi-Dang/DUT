package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.EducationModel;

import java.util.List;

public interface ICourseDAO {
    CourseModel findByCode(String code);
    Long insert(CourseModel courseModel);
    Long insertDependentCourse(CourseModel courseModel);
    CourseModel findById(Long id);
    CourseModel findByFacultyCode(String code);

    List<CourseModel> findAll();
    Long update(CourseModel courseModel);
    Long updateDependentCourse(CourseModel courseModel);
    void delete(Long id);
    Long deleteDepentdentCourse(Long id);
    CourseModel findAllCourseDependent(String courseCode);
    CourseModel findDepentdentCourseById(Long id);
}
