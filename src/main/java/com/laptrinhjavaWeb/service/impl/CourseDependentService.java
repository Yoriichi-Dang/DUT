package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.ICourseDependentDAO;
import com.laptrinhjavaWeb.model.CourseDependentModel;
import com.laptrinhjavaWeb.service.ICourseDependentService;

import javax.inject.Inject;

public class CourseDependentService implements ICourseDependentService {
    @Inject
    private ICourseDependentDAO courseDependentDAO;
    @Override
    public CourseDependentModel findAllCourseInEducationByStudentCode(String code) {
        return  courseDependentDAO.findAllCourseInEducationByStudentCode(code);
    }
}
