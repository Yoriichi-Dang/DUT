package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.ClassCourseStudentModel;

public interface IClassCourseStudentDAO {
    ClassCourseStudentModel findHistoryRegisterByCourseCode(String code);
    ClassCourseStudentModel findResultStudy(String CourseCode,String studentCode);
}
