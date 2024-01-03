package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.IAcademicDAO;
import com.laptrinhjavaWeb.dao.IFacultyDAO;
import com.laptrinhjavaWeb.mapper.AcademicMapper;
import com.laptrinhjavaWeb.mapper.ClassMapper;
import com.laptrinhjavaWeb.mapper.FacultyMapper;
import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;

import javax.inject.Inject;
import java.util.List;

public class AcademicDAO extends AbstractDAO<AcademicModel> implements IAcademicDAO {
    @Inject
    private IFacultyDAO facultyDAO;
    @Override
    public Long insert(AcademicModel academicModel) {
        StringBuilder sql= new StringBuilder("insert into academic(code,name,createBy) values(?,?,?);");
        Long id=insert(sql.toString(),academicModel.getCode(),academicModel.getName(),academicModel.getCreateBy());
       if(id!=-1L){
           sql=new StringBuilder("insert into listacademic(FacultyCode,AcademicCode,createBy) values(?,?,?);");
           FacultyModel facultyModel=facultyDAO.findByCode(academicModel.getFacultyCode());
          Long facultyAcademicId= insert(sql.toString(),academicModel.getFacultyCode(),academicModel.getCode(),academicModel.getCreateBy());
          if(facultyAcademicId==-1L)return -1L;
       }
        return id;
    }

    @Override
    public AcademicModel findById(Long id) {
        StringBuilder sql= new StringBuilder("select * from academic where id=?;");
        return findById(sql.toString(),new AcademicMapper(),id);
    }

    @Override
    public AcademicModel findByCode(String code) {
        StringBuilder sql= new StringBuilder("select * from academic where code=?;");
        return findByCode(sql.toString(),new AcademicMapper(),code);
    }

    @Override
    public AcademicModel findAllClassByCode(String code) {
        StringBuilder sql=new StringBuilder("select * from Class inner join listclass on Class.code=listclass.ClassCode where AcademicCode=?");
        AcademicModel academicModel=new AcademicModel();
        academicModel=findByCode(code);
        academicModel.setClassModelList(query(sql.toString(),new ClassMapper(),code));
        return academicModel;
    }


    @Override
    public List<AcademicModel> findAll() {
        StringBuilder sql=new StringBuilder("select * from academic;");
        return query(sql.toString(),new AcademicMapper());
    }

    @Override
    public void update(AcademicModel academicModel) {
        String sql="update academic set code=?,name=?, createDate=?,createBy=? ,modifiedDate=?,modifiedBy=? where id=?";
        update(sql,academicModel.getCode(),academicModel.getName(),academicModel.getCreateDate(),academicModel.getCreateBy(),academicModel.getModifiedDate(),academicModel.getModifiedBy(),academicModel.getId());
    }

    @Override
    public void delete(Long id) {
        String sql="delete from academic where id=?";
        update(sql,id);
    }
}
