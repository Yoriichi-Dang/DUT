package com.laptrinhjavaWeb.dao.impl;


import com.laptrinhjavaWeb.dao.IFacultyDAO;
import com.laptrinhjavaWeb.mapper.AcademicMapper;
import com.laptrinhjavaWeb.mapper.FacultyMapper;
import com.laptrinhjavaWeb.mapper.UserMapper;
import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.paging.Pageable;
import com.mysql.cj.util.StringUtils;

import java.util.List;

public class FacultyDAO extends AbstractDAO<FacultyModel> implements IFacultyDAO {


    @Override
    public FacultyModel findByCode(String code) {
      StringBuilder sql=new StringBuilder("select * from faculty where code=?");
      return findByCode(sql.toString(),new FacultyMapper(),code);
    }

    @Override
    public Long insert(FacultyModel facultyModel) {
        StringBuilder sql= new StringBuilder("insert into faculty(code,name,createBy) values(?,?,?);");
        Long id=insert(sql.toString(),facultyModel.getCode(),facultyModel.getName(),facultyModel.getCreateBy());
        return id;
    }

    @Override
    public FacultyModel findById(Long id) {
        StringBuilder sql=new StringBuilder("select * from faculty where id=?;");
        return findById(sql.toString(),new FacultyMapper(),id);
    }


    @Override
    public List<FacultyModel> findAll() {
        StringBuilder sql=new StringBuilder("select * from faculty order by faculty.code asc");

//        if(pageable.getSorter()!=null&&!StringUtils.isNullOrEmpty(pageable.getSorter().getSortName())){
//            sql.append(" order by "+pageable.getSorter().getSortName()+" "+pageable.getSorter().getSortBy());
//        }
//        if(pageable.getOffset()!=null&&pageable.getLimit()!=null){
//            sql.append(" limit "+pageable.getOffset()+", "+pageable.getLimit());
//        }
        return query(sql.toString(),new FacultyMapper());
    }

    @Override
    public FacultyModel findAllAcademicByCode(String code) {
        StringBuilder sql=new StringBuilder("select * from academic inner join listacademic on academic.code=listacademic.AcademicCode where FacultyCode=?");
        FacultyModel facultyModel=new FacultyModel();
        facultyModel=findByCode(code);
        facultyModel.setAcademicModelList(query(sql.toString(),new AcademicMapper(),code));
        return facultyModel;
    }


    @Override
    public void update(FacultyModel facultyModel) {
        String sql="update faculty set code=?,name=?, createDate=?,createBy=? ,modifiedDate=?,modifiedBy=? where id=?";
        update(sql,facultyModel.getCode(),facultyModel.getName(),facultyModel.getCreateDate(),facultyModel.getCreateBy(),facultyModel.getModifiedDate(),facultyModel.getModifiedBy(),facultyModel.getId());
    }

    @Override
    public void delete(Long id) {
        FacultyModel facultyModel=findById(id);
        String sql="delete from faculty where id=?";
        update(sql,id);
        sql="delete from listacademic where FacultyCode=?";
        update(sql,facultyModel.getCode());
    }
}
