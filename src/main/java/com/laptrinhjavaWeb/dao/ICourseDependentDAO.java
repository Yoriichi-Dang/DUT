package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.CourseDependentModel;

public interface ICourseDependentDAO {
    CourseDependentModel findAllCourseInEducationByStudentCode(String code);
}
