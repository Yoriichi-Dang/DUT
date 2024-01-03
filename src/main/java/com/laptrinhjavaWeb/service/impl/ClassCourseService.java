package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.IClassCourseDAO;
import com.laptrinhjavaWeb.dao.IEducationDAO;
import com.laptrinhjavaWeb.dao.IStudentDAO;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.StudentModel;
import com.laptrinhjavaWeb.service.IClassCourseService;
import com.laptrinhjavaWeb.service.IEducationService;
import com.laptrinhjavaWeb.service.IStudentService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ClassCourseService implements IClassCourseService {
    @Inject
    private IClassCourseDAO classCourseDAO;
    @Inject
    private IStudentDAO studentDAO;
    @Inject
    private IEducationDAO educationDAO;

    @Override
    public Long insert(ClassCourseModel classCourseModel) {
        Year currentYear = Year.now();
        Long id=-1L;
        for(int i=0;i<classCourseModel.getNumberCreate();i++){
            ClassCourseModel classCourseModel1=classCourseModel;
            classCourseModel1.setCode(classCourseModel.getCourseCode()+"."+currentYear.getValue()+"."+classCourseModel.getEnrollYear()+"."+(i+10));
             id=classCourseDAO.insert(classCourseModel1);
        }
        return id;
    }

    @Override
    public ClassCourseModel findById(Long id) {
        return classCourseDAO.findById(id);
    }

    @Override
    public ClassCourseModel findByCode(String code) {
        return classCourseDAO.findByCode(code);
    }

    @Override
    public List<ClassCourseModel> findAll() {
        return classCourseDAO.findAll();
    }

    @Override
    public Long update(ClassCourseModel classCourseModel) {
        classCourseModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return classCourseDAO.update(classCourseModel);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ClassCourseModel findAllByCourseCode(String code) {
        return classCourseDAO.findAllByCourseCode(code);
    }

    @Override
    public Long insertHistoryClassCourse(ClassCourseModel classCourseModel) {
        return classCourseDAO.insertHistoryClassCourse(classCourseModel);
    }

    @Override
    public ClassCourseModel findAllRegisterClassCourseByStudentCode(String studentCode)
    {
        ClassCourseModel classCourseModel=classCourseDAO.findAllRegisterClassCourseByStudentCode(studentCode);
        List<ClassCourseModel>list=new ArrayList<>();
        if(classCourseModel.getList()!=null){
            for(int i=0;i<classCourseModel.getList().size();i++){
                ClassCourseModel model=classCourseModel.getList().get(i);// lấy 1 lớp hc phần
               model.setList(findAllStudentInClassCouse(model.getCode()).getList());//trong list là danh sách sinh viên
                list.add(model);
            }
            classCourseModel.setList(list);
        }
        return classCourseModel;
    }

    @Override
    public ClassCourseModel findClassCourseStudied(ClassCourseModel classCourseModel) {
        return classCourseDAO.findClassCourseStudied(classCourseModel);
    }

    @Override
    public ClassCourseModel findAllStudentInClassCouse(String classcourseCode) {
        ClassCourseModel classCourseModel=findByCode(classcourseCode);
        classCourseModel.setList(classCourseDAO.findAllStudentInClassCouse(classcourseCode).getList());
        if(!classCourseModel.getList().isEmpty()){
            for(int i=0;i<classCourseModel.getList().size();i++){
                ClassCourseModel model=classCourseModel.getList().get(i);
                StudentModel studentModel=studentDAO.findStudentByCode(model.getStudentCode());
                studentModel.setEducationModel(educationDAO.findStudentEducationByCode(studentModel.getCode()));
                classCourseModel.getList().get(i).setStudentModel(studentModel);
            }
        }
        return classCourseModel;
    }

    @Override
    public Long deleteHistoryRegisterClassCourse(ClassCourseModel classCourseModel) {
        return classCourseDAO.deleteHistoryRegisterClassCourse(classCourseModel);
    }

    @Override
    public ClassCourseModel findAllByTeacherCode(String teacherCode) {
        return classCourseDAO.findAllByTeacherCode(teacherCode);
    }
}
