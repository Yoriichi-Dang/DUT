package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IAcademicDAO;
import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.service.IAcademicService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class AcademicService implements IAcademicService {
@Inject
private IAcademicDAO academicDAO;
    @Override
    public Long insert(AcademicModel academicModel) {
       return academicDAO.insert(academicModel);
    }

    @Override
    public AcademicModel findById(Long id) {
        return academicDAO.findById(id);
    }

    @Override
    public AcademicModel findByCode(String code) {
      return academicDAO.findByCode(code);
    }

    @Override
    public AcademicModel findAllClassByCode(String code) {
        return academicDAO.findAllClassByCode(code);
    }

    @Override
    public List<AcademicModel> findAll() {
        return academicDAO.findAll();
    }

    @Override
    public AcademicModel update(AcademicModel academicModel) {
        AcademicModel oldAcademic=academicDAO.findById(academicModel.getId());
        if(oldAcademic.getCreateBy()==null||oldAcademic.getCreateBy().isEmpty())oldAcademic.setCreateBy(academicModel.getModifiedBy());
        if(oldAcademic.getCreateDate()==null)oldAcademic.setCreateDate(new Timestamp(System.currentTimeMillis()));
        if(academicModel.getCode().isEmpty())academicModel.setCode(oldAcademic.getCode());
        if(academicModel.getName().isEmpty())academicModel.setName(oldAcademic.getName());
        academicModel.setCreateDate(oldAcademic.getCreateDate());
        academicModel.setCreateBy(oldAcademic.getCreateBy());
        academicModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//        System.out.println(facultyModel.getId()+" "+facultyModel.getCode()+" "+facultyModel.getName());
        academicDAO.update(academicModel);
        return academicDAO.findById(academicModel.getId());
    }

    @Override
    public void delete(Long id) {
        academicDAO.delete(id);
    }
}
