package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IStudentDAO;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.service.IStudentService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class StudentService implements IStudentService {
    @Inject
    private IStudentDAO studentDAO;
    @Override
    public Long insert(StudentModel studentModel) {
       return studentDAO.insert(studentModel);
    }

    @Override
    public StudentModel findStudentById(Long id) {
       return studentDAO.findStudentById(id);
    }

    @Override
    public StudentModel findStudentByCode(String code) {
        return studentDAO.findStudentByCode(code);
    }

    @Override
    public List<StudentModel> findAllStudentByClassCode(String classCode) {
        return null;
    }

    @Override
    public List<StudentModel> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public StudentModel update(StudentModel studentModel) {
        StudentModel oldStudent=studentDAO.findStudentById(studentModel.getId());
        if(oldStudent.getCreateDate()==null)studentModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
        if(oldStudent.getCreateBy()==null||oldStudent.getCreateBy().isEmpty())studentModel.setCreateBy(studentModel.getModifiedBy());
        if(studentModel.getImage()==null||studentModel.getImage().isEmpty())studentModel.setImage(oldStudent.getImage());
        studentModel.setCreateBy(oldStudent.getCreateBy());
        studentModel.setCreateDate(oldStudent.getCreateDate());
        studentModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        boolean updateCode=false;
        if(!oldStudent.getCode().equals(studentModel.getCode()))updateCode=true;
        Long status=studentDAO.update(studentModel,updateCode);
        if(status==-1L) return null;
        else return studentDAO.findStudentById(studentModel.getId());
    }

    @Override
    public void delete(Long id) {
        studentDAO.delete(id);
    }

    @Override
    public StudentModel findAllStudentByFacultyCode(String code) {
        return studentDAO.findAllStudentByFacultyCode(code);
    }

    @Override
    public StudentModel findStudentInFaculty(String facultyCode, String studentCode) {
        return studentDAO.findStudentInFaculty(facultyCode,studentCode);
    }
}
