package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.dao.ITeacherDAO;
import com.laptrinhjavaWeb.dao.IUserDAO;
import com.laptrinhjavaWeb.mapper.TeacherMapper;
import com.laptrinhjavaWeb.model.TeacherModel;
import com.laptrinhjavaWeb.model.UserModel;

import javax.inject.Inject;
import java.util.List;

public class TeacherDAO extends AbstractDAO<TeacherModel> implements ITeacherDAO {
    @Inject
    private IUserDAO userDAO;
    @Override
    public Long insert(TeacherModel teacherModel) {
        StringBuilder sql= new StringBuilder("insert into teacher(code,fullname,cccd,sex,birthday,level,specialized,homeTown,createBy) values(?,?,?,?,?,?,?,?,?);");
        if(findByCode(teacherModel.getCode())==null){
            Long id=insert(sql.toString(),teacherModel.getCode(),teacherModel.getName(),teacherModel.getCccd(),teacherModel.getSex(),teacherModel.getBirthday(),teacherModel.getLevel(),teacherModel.getSpecialized(),teacherModel.getHometown(),teacherModel.getCreateBy());
            if(id!=-1L){
                //add into faculty
                sql=new StringBuilder("insert into listteacher(FacultyCode,TeacherCode,createBy) values(?,?,?);");
                Long idFaculty=insert(sql.toString(),teacherModel.getFacultyCode(),teacherModel.getCode(),teacherModel.getCreateBy());
                //add account
                Long idAccount=userDAO.insert(new UserModel(teacherModel.getCode(),teacherModel.getCode(), SystemConstant.TEACHER.toUpperCase(),1));
                if(idAccount==-1||idFaculty==-1)return -1L;
            }
            return id;
        }
        else{
            System.out.println("Teacher exist");
            return -1L;
        }
    }

    @Override
    public TeacherModel findById(Long id) {
        String sql="select teacher.*, faculty.id as FacultyId, faculty.name as FacultyName,faculty.code as FacultyCode from teacher\n" +
                "inner join listteacher on teacher.code=listteacher.TeacherCode\n" +
                "inner join faculty on listteacher.FacultyCode=faculty.code\n" +
                "where teacher.id=?;";
        return findById(sql,new TeacherMapper(),id);
    }

    @Override
    public TeacherModel findByCode(String code) {
        String sql="select teacher.*, faculty.id as FacultyId, faculty.name as FacultyName,faculty.code as FacultyCode from teacher\n" +
                "inner join listteacher on teacher.code=listteacher.TeacherCode\n" +
                "inner join faculty on listteacher.FacultyCode=faculty.code\n" +
                "where teacher.code=?;";
        return findByCode(sql,new TeacherMapper(),code);
    }

    @Override
    public List<TeacherModel> findAll() {
        String sql="select teacher.*, faculty.id as FacultyId, faculty.name as FacultyName,faculty.code as FacultyCode from teacher\n" +
                "inner join listteacher on teacher.code=listteacher.TeacherCode\n" +
                "inner join faculty on listteacher.FacultyCode=faculty.code;";
        return query(sql,new TeacherMapper());
    }

    @Override
    public Long update(TeacherModel teacherModel, boolean updateCode) {
        String sql="update teacher set code=?,fullname=?,sex=?,birthday=?,address=?,district=?,city=?,phone=?,email=?,image=?,CCCD=?,level=?,specialized=?,language=?,homeTown=?,createDate=?,createBy=?,modifiedDate=?,modifiedBy=? where id=?";
        Long id= update(sql,teacherModel.getCode(),teacherModel.getName(),teacherModel.getSex(),teacherModel.getBirthday(),teacherModel.getAddress(),teacherModel.getDistrict(),teacherModel.getCity(),teacherModel.getPhone(),teacherModel.getEmail(),teacherModel.getImage(),teacherModel.getCccd(),teacherModel.getLevel(),teacherModel.getSpecialized(),teacherModel.getLanguage(),teacherModel.getHometown(),teacherModel.getCreateDate(),teacherModel.getCreateBy(),teacherModel.getModifiedDate(),teacherModel.getModifiedBy(),teacherModel.getId());
        if(id!=-1L){
            if(updateCode){
                //update account,userroles if update teacher code
                //update list teacher if update teacher code
            }
        }else return -1L;
        return 1L;
    }

    @Override
    public void delete(Long id) {

    }
}
