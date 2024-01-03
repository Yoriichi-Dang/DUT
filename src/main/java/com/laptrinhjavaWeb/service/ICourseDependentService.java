package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.CourseDependentModel;

public interface ICourseDependentService {
    CourseDependentModel findAllCourseInEducationByStudentCode(String code);

}
