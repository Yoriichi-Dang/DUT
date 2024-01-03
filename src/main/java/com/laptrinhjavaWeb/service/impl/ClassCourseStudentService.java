package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IClassCourseStudentDAO;
import com.laptrinhjavaWeb.model.ClassCourseStudentModel;
import com.laptrinhjavaWeb.service.IClassCourseStudentService;

import javax.inject.Inject;

public class ClassCourseStudentService implements IClassCourseStudentService {
    @Inject
    private IClassCourseStudentDAO classCourseStudentDAO;
    @Override
    public ClassCourseStudentModel findHistoryRegisterByCourseCode(String code) {
        return classCourseStudentDAO.findHistoryRegisterByCourseCode(code);
    }

    @Override
    public ClassCourseStudentModel findResultStudy(String CourseCode, String studentCode) {
        return classCourseStudentDAO.findResultStudy(CourseCode,studentCode);
    }
}
