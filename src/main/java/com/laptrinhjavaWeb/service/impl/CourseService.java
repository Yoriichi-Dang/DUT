package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.ICourseDAO;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.service.ICourseService;

import javax.inject.Inject;
import java.util.List;

public class CourseService implements ICourseService {
    @Inject
    private ICourseDAO courseDAO;
    @Override
    public CourseModel findByCode(String code) {
        return courseDAO.findByCode(code);
    }

    @Override
    public Long insert(CourseModel courseModel) {
       return courseDAO.insert(courseModel);
    }

    @Override
    public CourseModel findById(Long id) {
        return courseDAO.findById(id);
    }

    @Override
    public List<CourseModel> findAll() {
        return courseDAO.findAll();
    }

    @Override
    public Long update(CourseModel courseModel) {
        return courseDAO.update(courseModel);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CourseModel findByFacultyCode(String code) {
        return courseDAO.findByFacultyCode(code);
    }

    @Override
    public CourseModel findAllCourseDependent(String courseCode) {
        return courseDAO.findAllCourseDependent(courseCode);
    }

    @Override
    public Long insertDependentCourse(CourseModel courseModel) {
        return courseDAO.insertDependentCourse(courseModel);
    }

    @Override
    public Long updateDependentCourse(CourseModel courseModel) {
        return courseDAO.updateDependentCourse(courseModel);
    }

    @Override
    public CourseModel findDepentdentCourseById(Long id) {
        return courseDAO.findDepentdentCourseById(id);
    }

    @Override
    public Long deleteDepentdentCourse(Long id) {
       return courseDAO.deleteDepentdentCourse(id);
    }
}
