package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IRegisterCourseDAO;
import com.laptrinhjavaWeb.model.TimeRegisterCourseModel;
import com.laptrinhjavaWeb.service.IRegisterCourseService;

import javax.inject.Inject;
import java.sql.Timestamp;

public class RegisterCourseService implements IRegisterCourseService {
    @Inject
    private IRegisterCourseDAO registerCourseDAO;
    @Override
    public TimeRegisterCourseModel findById(Long id) {
        return registerCourseDAO.findById(id);
    }

    @Override
    public Long insert(TimeRegisterCourseModel timeRegisterCourseModel) {
        return registerCourseDAO.insert(timeRegisterCourseModel);
    }

    @Override
    public Long update(TimeRegisterCourseModel timeRegisterCourseModel) {
        return registerCourseDAO.update(timeRegisterCourseModel);
    }

    @Override
    public Long delete(Long id) {
        return registerCourseDAO.delete(id);
    }

    @Override
    public TimeRegisterCourseModel findAll() {
        return registerCourseDAO.findAll();
    }

    @Override
    public TimeRegisterCourseModel findByStartTime(Timestamp timestamp) {
        return registerCourseDAO.findByStartTime(timestamp);
    }
}
