package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IClassDAO;
import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.service.IClassService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class ClassService implements IClassService {
@Inject
private IClassDAO classDAO;
    @Override
    public Long insert(ClassModel classModel) {
      return classDAO.insert(classModel);
    }

    @Override
    public ClassModel findClasById(Long id) {
        return classDAO.findById(id);
    }

    @Override
    public ClassModel findClassCode(String code) {
        return classDAO.findByCode(code);
    }

    @Override
    public List<ClassModel> findAll() {
       return classDAO.findAll();
    }

    @Override
    public ClassModel update(ClassModel classModel) {
        ClassModel oldClass=classDAO.findById(classModel.getId());
        if(oldClass.getCreateBy()==null||oldClass.getCreateBy().isEmpty())oldClass.setCreateBy(classModel.getModifiedBy());
        if(oldClass.getCreateDate()==null)oldClass.setCreateDate(new Timestamp(System.currentTimeMillis()));
        if(classModel.getCode().isEmpty())classModel.setCode(oldClass.getCode());
        classModel.setCreateDate(oldClass.getCreateDate());
        classModel.setCreateBy(oldClass.getCreateBy());
        classModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//        System.out.println(facultyModel.getId()+" "+facultyModel.getCode()+" "+facultyModel.getName());
        classDAO.update(classModel);
        return classDAO.findById(classModel.getId());
    }

    @Override
    public void delete(Long id) {
        classDAO.delete(id);
    }

    @Override
    public ClassModel findAllStudentByClassCode(String code) {
       return classDAO.findAllStudentByClassCode(code);
    }
}
