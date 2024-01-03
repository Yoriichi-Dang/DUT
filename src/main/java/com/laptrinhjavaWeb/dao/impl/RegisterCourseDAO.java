package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.IFacultyDAO;
import com.laptrinhjavaWeb.dao.IRegisterCourseDAO;
import com.laptrinhjavaWeb.mapper.TimeRegisterCourseMapper;
import com.laptrinhjavaWeb.model.FacultyModel;
import com.laptrinhjavaWeb.model.TimeRegisterCourseModel;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class RegisterCourseDAO extends AbstractDAO<TimeRegisterCourseModel> implements IRegisterCourseDAO {
   @Inject
   private IFacultyDAO facultyDAO;
    @Override
    public TimeRegisterCourseModel findById(Long id) {
        return null;
    }

    @Override
    public Long insert(TimeRegisterCourseModel timeRegisterCourseModel) {
        String sql="insert into registercourse(typeRegister,value,datetimeRegister,endRegister,createBy) values(?,?,?,?,?);";
        Long id=insert(sql,timeRegisterCourseModel.getTypeRegister(),timeRegisterCourseModel.getValue(),timeRegisterCourseModel.getDateTimeRegister(),timeRegisterCourseModel.getEndRegister(),timeRegisterCourseModel.getCreateBy());
        if(id==-1L)return -1L;
        return id;
    }

    @Override
    public Long update(TimeRegisterCourseModel timeRegisterCourseModel) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public TimeRegisterCourseModel findAll() {
        String sql="select * from registercourse";
        TimeRegisterCourseModel timeRegisterCourseModel=new TimeRegisterCourseModel();
        List<TimeRegisterCourseModel> courseModelList=query(sql,new TimeRegisterCourseMapper());
        for(int i=0;i<courseModelList.size();i++){
            if(courseModelList.get(i).getTypeRegister().equals("FACULTY")){
                FacultyModel facultyModel=new FacultyModel();
                facultyModel=facultyDAO.findByCode(courseModelList.get(i).getValue());
                courseModelList.get(i).setFacultyModel(facultyModel);
            }
        }
        timeRegisterCourseModel.setList(courseModelList);
        return timeRegisterCourseModel;
    }

    @Override
    public TimeRegisterCourseModel findByStartTime(Timestamp timestamp) {
        String sql= "SELECT *\n" +
                "FROM registercourse\n" +
                "WHERE datetimeRegister <= ?  AND ? <= endRegister\n" +
                "ORDER BY datetimeRegister DESC\n" +
                "LIMIT 1;\n";
        List<TimeRegisterCourseModel>timeRegisterCourseModels=query(sql,new TimeRegisterCourseMapper(),timestamp,timestamp);
        if(timeRegisterCourseModels!=null&&!timeRegisterCourseModels.isEmpty())return timeRegisterCourseModels.get(0);
        return null;
    }
}
