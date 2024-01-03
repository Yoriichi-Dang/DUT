package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.IAcademicDAO;
import com.laptrinhjavaWeb.dao.IClassDAO;
import com.laptrinhjavaWeb.mapper.AcademicMapper;
import com.laptrinhjavaWeb.mapper.ClassMapper;
import com.laptrinhjavaWeb.mapper.FacultyMapper;
import com.laptrinhjavaWeb.mapper.StudentMapper;
import com.laptrinhjavaWeb.model.*;

import javax.inject.Inject;
import java.util.List;

public class ClassDAO extends AbstractDAO<ClassModel> implements IClassDAO {

    @Inject
    private IAcademicDAO academicDAO;

    @Override
    public ClassModel findByCode(String code) {
        StringBuilder sql=new StringBuilder("select * from class where code=?");
        return findByCode(sql.toString(),new ClassMapper(),code);
    }

    @Override
    public Long insert(ClassModel classModel) {
        StringBuilder sql= new StringBuilder("insert into class(code,createBy) values(?,?);");
        Long id=insert(sql.toString(),classModel.getCode(),classModel.getCreateBy());
        if(id!=-1L){
            sql=new StringBuilder("insert into listclass(AcademicCode,ClassCode,createBy) values(?,?,?);");
            AcademicModel academicModel=academicDAO.findByCode(classModel.getAcademicCode());
            Long academicClassId= insert(sql.toString(),academicModel.getCode(),classModel.getCode(),classModel.getCreateBy());
            if(academicClassId==-1L)return -1L;
        }
        return id;
    }

    @Override
    public ClassModel findById(Long id) {
        StringBuilder sql=new StringBuilder("select * from class where id=?");
        return findById(sql.toString(),new ClassMapper(),id);
    }

    @Override
    public List<ClassModel> findAll() {
        StringBuilder sql=new StringBuilder("select * from class");
        return query(sql.toString(),new ClassMapper());
    }

    @Override
    public ClassModel findAllStudentByClassCode(String code) {
        StringBuilder sql=new StringBuilder("select student.*,liststudent.ClassCode as ClassCode from student\n" +
                "inner join liststudent on student.code=liststudent.StudentCode\n" +
                "where liststudent.ClassCode=? order by student.code asc;");
        ClassModel classModel=new ClassModel();
        classModel=findByCode(code);
        List<StudentModel>studentModelList=query(sql.toString(),new StudentMapper(),code);
        if(studentModelList.size()!=0){
            classModel.setStudentModelList(studentModelList);
        }else{
            classModel.setStudentModelList(null);
        }
        return classModel;
    }

    @Override
    public void update(ClassModel classModel) {
        String sql="update class set code=?, createDate=?,createBy=? ,modifiedDate=?,modifiedBy=? where id=?";
        update(sql,classModel.getCode(),classModel.getCreateDate(),classModel.getCreateBy(),classModel.getModifiedDate(),classModel.getModifiedBy(),classModel.getId());
    }

    @Override
    public void delete(Long id) {
        String sql="delete from class where id=?";
        update(sql,id);
    }
}
