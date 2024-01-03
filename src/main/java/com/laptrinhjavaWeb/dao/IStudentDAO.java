package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.ClassModel;
import com.laptrinhjavaWeb.model.StudentModel;

import java.util.List;

public interface IStudentDAO {
    Long insert(StudentModel studentModel);
    StudentModel findStudentById(Long id);
    StudentModel findStudentByCode(String code);

    List<StudentModel> findAll();
    Long update(StudentModel studentModel,boolean updateCode);
    void delete(Long id);
    StudentModel findAllStudentByFacultyCode(String code);
    StudentModel findStudentInFaculty(String facultyCode,String studentCode);
}
