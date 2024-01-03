package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.ICourseDAO;
import com.laptrinhjavaWeb.mapper.CourseMapper;
import com.laptrinhjavaWeb.mapper.EducationMapper;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.EducationModel;

import java.util.List;

public class CourseDAO extends AbstractDAO<CourseModel> implements ICourseDAO {
    @Override
    public CourseModel findByCode(String code) {
        String sql="select course.*,facultycourse.id as FacultyId,faculty.code as FacultyCode, faculty.name as FacultyName from course\n" +
                "inner join facultycourse on course.code=facultycourse.CourseCode\n" +
                "inner join faculty on facultycourse.FacultyCode=faculty.code\n" +
                "where course.code=?;";
        return findByCode(sql,new CourseMapper(),code);
    }

    @Override
    public Long insert(CourseModel courseModel) {
        String sql="insert into course(code,name,numberCredit,pointStudy,BT,BV,CK,DA,GK,QT,TH,createBy) values(?,?,?,?,?,?,?,?,?,?,?,?);";
        Long id=insert(sql,courseModel.getCode().trim(),courseModel.getName(),courseModel.getNumberCredit(),courseModel.getPointStudy(),courseModel.getPointBT(),courseModel.getPointBV(),courseModel.getPointCK(),courseModel.getPointDA(),courseModel.getPointGK(),courseModel.getPointQT(),courseModel.getPointTH(),courseModel.getCreateBy());
        if(id!=-1L){
            sql="insert into facultycourse(FacultyCode,CourseCode,createBy) values(?,?,?)";
            Long idFacultyCourseId=insert(sql,courseModel.getFacultyCode(),courseModel.getCode(),courseModel.getCreateBy());
            if(idFacultyCourseId==-1L)return -1L;
        }else return -1L;
        return id;
    }

    @Override
    public Long insertDependentCourse(CourseModel courseModel) {
        String sql="insert into listcourse(CourseCode,DependentCourseCode,type,createBy) values(?,?,?,?);";
        Long id=insert(sql,courseModel.getCode().trim(),courseModel.getDependentCourseCode().trim(),courseModel.getTypeCourse().trim(),courseModel.getCreateBy());
        return id!=null?id:-1L;
    }

    @Override
    public CourseModel findById(Long id) {
        String sql="select course.*,facultycourse.id as FacultyId,faculty.code as FacultyCode, faculty.name as FacultyName from course\n" +
                "inner join facultycourse on course.code=facultycourse.CourseCode\n" +
                "inner join faculty on facultycourse.FacultyCode=faculty.code\n" +
                "where course.id=?;";
        return findById(sql,new CourseMapper(),id);
    }

    @Override
    public CourseModel findByFacultyCode(String code) {
        String sql="select course.*,faculty.id as FacultyId,faculty.code as FacultyCode, faculty.name as FacultyName from course \n" +
                "inner join facultycourse on facultycourse.CourseCode=course.code\n" +
                "inner join faculty on facultycourse.FacultyCode=faculty.code\n" +
                "where faculty.code=? order by course.code asc;";
        List<CourseModel>list= query(sql,new CourseMapper(),code);
        CourseModel courseModel=new CourseModel();
        courseModel.setFacultyCode(code);
        courseModel.setList(list);
        return courseModel;
    }

    @Override
    public List<CourseModel> findAll() {
       String sql="select course.*,faculty.id as FacultyId,faculty.code as FacultyCode, faculty.name as FacultyName from course\n" +
               "inner join facultycourse on course.code=facultycourse.CourseCode\n" +
               "inner join faculty on facultycourse.FacultyCode=faculty.code order by course.code asc;";
        return query(sql,new CourseMapper());
    }

    @Override
    public Long update(CourseModel courseModel) {
        String sql="update course set name=?,numberCredit=?,pointStudy=?,BT=?,BV=?,CK=?,DA=?,GK=?,QT=?,TH=? ,modifiedDate=?,modifiedBy=? where id=?";
        CourseModel oldCourseModel=findById(courseModel.getId());
        //thay đổi CourseCode

        //không thay đổi chỉ thay đổi thông tin
        Long id=-1L;
        if(oldCourseModel.getCode().equals(courseModel.getCode())){
            //cập nhật thông tin
            id=update(sql,courseModel.getName(),courseModel.getNumberCredit(),courseModel.getPointStudy(),courseModel.getPointBT(),courseModel.getPointBV(),courseModel.getPointCK(),courseModel.getPointDA(),courseModel.getPointGK(),courseModel.getPointQT(),courseModel.getPointTH(),courseModel.getModifiedDate(),courseModel.getModifiedBy(),courseModel.getId());
            //so sánh code khoa cũ và mới nếu khác thì thay đổi
            if(!oldCourseModel.getFacultyModel().getCode().equals(oldCourseModel.getFacultyCode())){
                sql="update facultycourse set FacultyCode=? where id=?";
                id=update(sql,courseModel.getFacultyCode(),oldCourseModel.getFacultyModel().getId());
                if(id==-1L)return -1L;
            }
        }
        return id;
    }

    @Override
    public Long updateDependentCourse(CourseModel courseModel) {
        String sql="update listcourse set type=?,modifiedDate=?,modifiedBy=? where id=?";
        Long id=update(sql,courseModel.getTypeCourse(),courseModel.getModifiedDate(),courseModel.getModifiedBy(),courseModel.getId());
        return id;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Long deleteDepentdentCourse(Long id) {
        String sql="delete from listcourse where id=?";
        return update(sql,id);
    }

    @Override
    public CourseModel findAllCourseDependent(String courseCode) {
        String sql="select listcourse.id as id,listcourse.DependentCourseCode as code,course.name as name,listcourse.type as type,course.numberCredit as numberCredit from listcourse\n" +
                "inner join course on listcourse.DependentCourseCode=course.code\n" +
                "where listcourse.CourseCode=?";
        List<CourseModel>courseModelList=query(sql,new CourseMapper(),courseCode.trim());
        CourseModel courseModel=findByCode(courseCode.trim());
        courseModel.setList(courseModelList);
        return courseModel;
    }

    @Override
    public CourseModel findDepentdentCourseById(Long id) {
        String sql="select listcourse.id as id, listcourse.CourseCode as code , listcourse.type as type, course.name as name\n" +
                "from listcourse\n" +
                "inner join course on listcourse.DependentCourseCode=course.code\n" +
                "where listcourse.id=?";
        CourseModel courseModel=findById(sql,new CourseMapper(),id);
        courseModel.setId(id);
        return courseModel;
    }


}
