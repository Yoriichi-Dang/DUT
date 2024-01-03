package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.StudentModel;

import java.util.List;

public interface IStudentService {
    Long insert(StudentModel studentModel);
    StudentModel findStudentById(Long id);
    StudentModel findStudentByCode(String code);
    List<StudentModel>findAllStudentByClassCode(String classCode);

    List<StudentModel> findAll();
    StudentModel update(StudentModel studentModel);
    void delete(Long id);
    StudentModel findAllStudentByFacultyCode(String code);
    StudentModel findStudentInFaculty(String facultyCode,String studentCode);
}
