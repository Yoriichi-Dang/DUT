package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.TimeRegisterCourseModel;

import java.sql.Timestamp;

public interface IRegisterCourseDAO {
    TimeRegisterCourseModel findById(Long id);
    Long insert(TimeRegisterCourseModel timeRegisterCourseModel);
    Long update(TimeRegisterCourseModel timeRegisterCourseModel);
    Long delete(Long id);
    TimeRegisterCourseModel findAll();
    TimeRegisterCourseModel findByStartTime(Timestamp timestamp);
}
