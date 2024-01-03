package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IEducationDAO;
import com.laptrinhjavaWeb.model.EducationModel;
import com.laptrinhjavaWeb.service.IEducationService;

import javax.inject.Inject;
import java.util.List;

public class EducationService implements IEducationService {
    @Inject
    private IEducationDAO educationDAO;
    @Override
    public Long insert(EducationModel educationModel) {
        if(educationModel.getTypeEducation().equals("1")){
            educationModel.setTypeEducation("Cử nhân");
        }
        else if(educationModel.getTypeEducation().equals("2")){
            educationModel.setTypeEducation("Kỹ sư");
        }
        return educationDAO.insert(educationModel);
    }

    @Override
    public EducationModel findById(Long id) {
        return educationDAO.findById(id);
    }

    @Override
    public EducationModel findByCode(String code) {
        return educationDAO.findByCode(code);
    }

    @Override
    public EducationModel findByFacultyCode(String code) {
        return educationDAO.findByFacultyCode(code);
    }

    @Override
    public List<EducationModel> findAll() {
        return educationDAO.findAll();
    }

    @Override
    public Long insertEducationCourse(EducationModel educationModel) {
        return educationDAO.insertEducationCourse(educationModel);
    }

    @Override
    public EducationModel findAllCourseByCode(String code) {
        return educationDAO.findAllCourseByCode(code);
    }

    @Override
    public EducationModel findAllEducationCourseByFacultyCode(EducationModel educationModel) {
        return educationDAO.findAllEducationCourseByFacultyCode(educationModel);
    }

    @Override
    public EducationModel findAllEducationByStudentCode(String code) {
        return educationDAO.findAllEducationByStudentCode(code);
    }

    @Override
    public void update(EducationModel educationModel) {

    }

    @Override
    public void delete(Long id) {

    }
}
