package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.ITeacherDAO;
import com.laptrinhjavaWeb.model.TeacherModel;
import com.laptrinhjavaWeb.service.ITeacherService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class TeacherService implements ITeacherService {
    @Inject
    private ITeacherDAO teacherDAO;
    @Override
    public Long insert(TeacherModel teacherModel) {
        return teacherDAO.insert(teacherModel);
    }

    @Override
    public TeacherModel findById(Long id) {
        return teacherDAO.findById(id);
    }

    @Override
    public TeacherModel findByCode(String code) {
        return teacherDAO.findByCode(code);
    }

    @Override
    public List<TeacherModel> findAll() {
        return teacherDAO.findAll();
    }

    @Override
    public TeacherModel update(TeacherModel teacherModel) {
        TeacherModel oldTeacher=teacherDAO.findById(teacherModel.getId());
        if(oldTeacher.getCreateDate()==null)teacherModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
        if(oldTeacher.getCreateBy()==null||oldTeacher.getCreateBy().isEmpty())teacherModel.setCreateBy(teacherModel.getModifiedBy());
        if(teacherModel.getImage()==null||teacherModel.getImage().isEmpty())teacherModel.setImage(oldTeacher.getImage());
        teacherModel.setCreateBy(oldTeacher.getCreateBy());
        teacherModel.setCreateDate(oldTeacher.getCreateDate());
        teacherModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        boolean updateCode=false;
        if(!oldTeacher.getCode().equals(oldTeacher.getCode()))updateCode=true;
        Long status=teacherDAO.update(teacherModel,updateCode);
        if(status==-1L) return null;
        else return teacherDAO.findById(teacherModel.getId());
    }

    @Override
    public void delete(Long id) {
        teacherDAO.delete(id);
    }
}
