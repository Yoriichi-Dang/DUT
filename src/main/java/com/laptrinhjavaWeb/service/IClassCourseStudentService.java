package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.ClassCourseStudentModel;

public interface IClassCourseStudentService {
    ClassCourseStudentModel findHistoryRegisterByCourseCode(String code);
    ClassCourseStudentModel findResultStudy(String CourseCode,String studentCode);
}
