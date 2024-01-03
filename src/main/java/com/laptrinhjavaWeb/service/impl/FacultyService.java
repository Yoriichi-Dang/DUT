package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IFacultyDAO;
import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.service.IFacultyService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class FacultyService implements IFacultyService {
    @Inject
    private IFacultyDAO FacultyDAO;

    @Override
    public FacultyModel findByCode(String code) {
       return FacultyDAO.findByCode(code);
    }

    @Override
    public Long insert(FacultyModel facultyModel) {
        return FacultyDAO.insert(facultyModel);
    }

    @Override
    public FacultyModel findById(Long id) {
       return FacultyDAO.findById(id);
    }


    @Override
    public List<FacultyModel> findAll() {
       return FacultyDAO.findAll();
    }

    @Override
    public FacultyModel findAllAcademicByCode(String code) {
       return FacultyDAO.findAllAcademicByCode(code);
    }

    @Override
    public FacultyModel update(FacultyModel facultyModel) {
        FacultyModel oldFaculty=FacultyDAO.findById(facultyModel.getId());
        if(oldFaculty.getCreateBy()==null||oldFaculty.getCreateBy().isEmpty())oldFaculty.setCreateBy(facultyModel.getModifiedBy());
        if(oldFaculty.getCreateDate()==null)oldFaculty.setCreateDate(new Timestamp(System.currentTimeMillis()));
        if(facultyModel.getCode().isEmpty())facultyModel.setCode(oldFaculty.getCode());
        if(facultyModel.getName().isEmpty())facultyModel.setName(oldFaculty.getName());
        facultyModel.setCreateDate(oldFaculty.getCreateDate());
        facultyModel.setCreateBy(oldFaculty.getCreateBy());
        facultyModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//        System.out.println(facultyModel.getId()+" "+facultyModel.getCode()+" "+facultyModel.getName());
        FacultyDAO.update(facultyModel);
        return FacultyDAO.findById(facultyModel.getId());
    }

    @Override
    public void delete(Long id) {
        FacultyDAO.delete(id);
    }


}
