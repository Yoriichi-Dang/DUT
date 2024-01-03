package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.IEducationDAO;
import com.laptrinhjavaWeb.mapper.CourseMapper;
import com.laptrinhjavaWeb.mapper.EducationMapper;
import com.laptrinhjavaWeb.model.AcademicModel;
import com.laptrinhjavaWeb.model.CourseModel;
import com.laptrinhjavaWeb.model.EducationModel;

import java.util.List;

public class EducationDAO extends AbstractDAO<EducationModel> implements IEducationDAO {

    @Override
    public Long insert(EducationModel educationModel) {
        String sql="insert into education(code,name,semesterStudy,typeEducation,createBy) values(?,?,?,?,?);";
        Long id=insert(sql,educationModel.getCode(),educationModel.getName(),educationModel.getSemesterStudy(),educationModel.getTypeEducation(),educationModel.getCreateBy());
        if(id!=-1L){
            sql="insert into educationacademic(AcademicCode,EducationCode,createBy) values(?,?,?);";
            Long idEducationAcademic=insert(sql,educationModel.getAcademicCode(),educationModel.getCode(),educationModel.getCreateBy());
            if(idEducationAcademic==-1L)return -1L;
        }else return -1L;
        return id;
    }

    @Override
    public EducationModel findById(Long id) {
        String sql="select education.*,academic.id as AcademicId ,academic.code as AcademicCode, academic.name as AcademicName\n" +
                "from education \n" +
                "inner join educationacademic on education.code=educationacademic.EducationCode\n" +
                "inner join academic on educationacademic.AcademicCode=academic.code\n" +
                "where education.id=?;";
        return findById(sql,new EducationMapper(),id);
    }

    @Override
    public EducationModel findByCode(String code) {
        String sql="select education.*,academic.id as AcademicId ,academic.code as AcademicCode, academic.name as AcademicName\n" +
                "from education \n" +
                "inner join educationacademic on education.code=educationacademic.EducationCode\n" +
                "inner join academic on educationacademic.AcademicCode=academic.code\n" +
                "where education.code=?;";
        return findByCode(sql,new EducationMapper(),code);
    }

    @Override
    public EducationModel findByFacultyCode(String code) {
        String sql="select education.*,academic.id as AcademicId ,academic.code as AcademicCode, academic.name as AcademicName\n" +
                "from education \n" +
                "inner join educationacademic on education.code=educationacademic.EducationCode\n" +
                "inner join academic on educationacademic.AcademicCode=academic.code\n" +
                "inner join listacademic on academic.code=listacademic.AcademicCode\n" +
                "inner join faculty on listacademic.FacultyCode=faculty.code where faculty.code=? order by education.code asc;";
        List<EducationModel>list= query(sql,new EducationMapper(),code);
        EducationModel educationModel=new EducationModel();
        educationModel.setFacultyCode(code);
        educationModel.setList(list);
        return educationModel;
    }

    @Override
    public Long insertEducationCourse(EducationModel educationModel) {
        Long id=-1L;
        String sql="insert into educationcourse(EducationCode,CourseCode,semester,createBy) values(?,?,?,?);";
        for(int i=0;i<educationModel.getListCourseCode().size();i++){
            id=insert(sql,educationModel.getCode(),educationModel.getListCourseCode().get(i),educationModel.getSemesterStudy(),educationModel.getCreateBy());
            if(id==-1L)return -1L;
        }
        return id;
    }

    @Override
    public EducationModel findAllCourseByCode(String code) {
        String sql="select course.* from course\n" +
                "inner join educationcourse on course.code=educationcourse.CourseCode\n" +
                "inner join education on educationcourse.EducationCode=education.code\n" +
                "where education.code=?;";
        EducationModel educationModel=new EducationModel();
        List<CourseModel>courseModelList=query(sql,new CourseMapper(),code);
        educationModel=findByCode(code);
        educationModel.setCourseModelList(courseModelList);
        return educationModel;
    }

    @Override
    public EducationModel findAllEducationCourseByFacultyCode(EducationModel educationModel) {
        String sql="select course.* from faculty\n" +
                "inner join facultycourse on faculty.code=facultycourse.FacultyCode\n" +
                "inner join course on facultycourse.CourseCode=course.code\n" +
                "inner join educationcourse on course.code=educationcourse.CourseCode\n" +
                "inner join education on educationcourse.EducationCode=education.code\n" +
                "where faculty.code=? and education.code=? order by course.code asc;";
        List<CourseModel>courseModelList=query(sql,new CourseMapper(),educationModel.getFacultyCode(),educationModel.getCode());
        educationModel.setCourseModelList(courseModelList);
        return educationModel;
    }

    @Override
    public EducationModel findStudentEducationByCode(String code) {
       String sql="SELECT\n" +
               "    education.*,academic.id as AcademicId, academic.code as AcademicCode, academic.name as AcademicName\n" +
               "FROM\n" +
               "    education\n" +
               "INNER JOIN\n" +
               "    educationstudent ON education.code = educationstudent.EducationCode\n" +
               "INNER JOIN\n" +
               "    student ON educationstudent.StudentCode = student.code\n" +
               "inner join educationacademic on education.code=educationacademic.EducationCode\n" +
               "inner join academic on educationacademic.AcademicCode=academic.code\n" +
               "WHERE\n" +
               "    student.code =?";
        return findByCode(sql,new EducationMapper(),code);
    }

    @Override
    public EducationModel findAllEducationByStudentCode(String code) {
        String sql="select education.*,academic.id as AcademicId, academic.code as AcademicCode, academic.name as AcademicName from education\n" +
                "inner join educationacademic on education.code =educationacademic.EducationCode\n" +
                "inner join academic on educationacademic.AcademicCode=academic.code\n" +
                "inner join listclass on academic.code=listclass.AcademicCode\n" +
                "inner join liststudent on listclass.ClassCode=liststudent.ClassCode\n" +
                "inner join student on liststudent.StudentCode=student.code\n" +
                "where student.code=?;";
        EducationModel educationModel=new EducationModel();
        educationModel.setList(query(sql,new EducationMapper(),code));
        return educationModel;
    }

    @Override
    public List<EducationModel> findAll() {
        String sql="select education.*,academic.id as AcademicId ,academic.code as AcademicCode, academic.name as AcademicName\n" +
                "from education \n" +
                "inner join educationacademic on education.code=educationacademic.EducationCode\n" +
                "inner join academic on educationacademic.AcademicCode=academic.code order by education.code asc;";
        return query(sql,new EducationMapper());
    }
    @Override
    public void update(EducationModel educationModel) {

    }

    @Override
    public void delete(Long id) {

    }

}
