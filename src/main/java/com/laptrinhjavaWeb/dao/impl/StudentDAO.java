package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.dao.IClassDAO;
import com.laptrinhjavaWeb.dao.IEducationDAO;
import com.laptrinhjavaWeb.dao.IStudentDAO;
import com.laptrinhjavaWeb.dao.IUserDAO;
import com.laptrinhjavaWeb.mapper.AcademicMapper;
import com.laptrinhjavaWeb.mapper.EducationMapper;
import com.laptrinhjavaWeb.mapper.FacultyMapper;
import com.laptrinhjavaWeb.mapper.StudentMapper;
import com.laptrinhjavaWeb.model.*;
import com.laptrinhjavaWeb.service.IUserService;

import javax.inject.Inject;
import java.util.List;

public class StudentDAO extends AbstractDAO<StudentModel> implements IStudentDAO {
    @Inject
    private IUserDAO userDAO;
    @Inject
    private IClassDAO classDAO;
    @Inject
    private IEducationDAO educationDAO;
    @Override
    public Long insert(StudentModel studentModel) {
        StringBuilder sql= new StringBuilder("insert into student(code,fullname,cccd,sex,birthday,EnrollmentYear,createBy) values(?,?,?,?,?,?,?);");
        if(findStudentByCode(studentModel.getCode())==null){
            Long id=insert(sql.toString(),studentModel.getCode(),studentModel.getName(),studentModel.getCccd(),studentModel.getSex(),studentModel.getBirthday(),studentModel.getEnrollmentYear(),studentModel.getCreateBy());
            if(id!=-1L){
                //add into class
                sql=new StringBuilder("insert into liststudent(ClassCode,StudentCode,createBy) values(?,?,?);");
                Long idClass=insert(sql.toString(),studentModel.getClassCode(),studentModel.getCode(),studentModel.getCreateBy());
                //add account
                UserModel userModel=new UserModel(studentModel.getCode(),studentModel.getCode(),SystemConstant.STUDENT.toUpperCase(),1);
                userModel.setCreateBy(studentModel.getCreateBy());
                Long idAccount=userDAO.insert(userModel);
                if(idAccount==-1||idClass==-1)return -1L;
                //add education default
                sql=new StringBuilder("select education.* from education\n" +
                        "inner join educationacademic on education.code=educationacademic.EducationCode\n" +
                        "inner join listclass on educationacademic.AcademicCode=listclass.AcademicCode\n" +
                        "inner join liststudent on listclass.ClassCode=liststudent.ClassCode\n" +
                        "where typeEducation='Cử nhân' and liststudent.StudentCode=? ;");
                EducationModel educationModel=query(sql.toString(),new EducationMapper(),studentModel.getCode()).get(0);
               if(educationModel!=null){
                   sql=new StringBuilder("insert into educationstudent(EducationCode,StudentCode,createBy) values(?,?,?)");
                   Long idEducationStudent=insert(sql.toString(),educationModel.getCode(),studentModel.getCode(),studentModel.getCreateBy());
                   if(idEducationStudent==-1L)return -1L;
               }else return -1L;
            }
            return id;
        }
        else{
            System.out.println("Student exist");
        return -1L;
        }
    }

    @Override
    public StudentModel findStudentById(Long id) {
     String sql="SELECT \n" +
             "    student.*,\n" +
             "    liststudent.ClassCode AS ClassCode,\n" +
             "     academic.code as AcademicCode,\n" +
             "    academic.name as AcademicName,\n" +
             "    CEIL(TIMESTAMPDIFF(YEAR, student.createDate, NOW()) + 1) AS YearStudy\n" +
             "FROM \n" +
             "    student\n" +
             "INNER JOIN \n" +
             "    liststudent ON student.code = liststudent.StudentCode\n" +
             "INNER JOIN \n" +
             "    class ON liststudent.ClassCode = class.code\n" +
             "inner join listclass on class.code=listclass.ClassCode\n" +
             "inner join academic on listclass.AcademicCode=academic.code where student.id=?;";
       StudentModel studentModel= findById(sql,new StudentMapper(),id);
       if(studentModel.getCode()!=null){
            EducationModel educationModel=educationDAO.findStudentEducationByCode(studentModel.getCode());
            studentModel.setEducationModel(educationModel);
       }
       return studentModel;
    }

    @Override
    public StudentModel findStudentByCode(String code) {
        StringBuilder sql= new StringBuilder("SELECT \n" +
                "    student.*,\n" +
                "    liststudent.ClassCode AS ClassCode,\n" +
                "     academic.code as AcademicCode,\n" +
                "    academic.name as AcademicName,\n" +
                "    CEIL(TIMESTAMPDIFF(YEAR, student.createDate, NOW()) + 1) AS YearStudy\n" +
                "FROM \n" +
                "    student\n" +
                "INNER JOIN \n" +
                "    liststudent ON student.code = liststudent.StudentCode\n" +
                "INNER JOIN \n" +
                "    class ON liststudent.ClassCode = class.code\n" +
                "inner join listclass on class.code=listclass.ClassCode\n" +
                "inner join academic on listclass.AcademicCode=academic.code where student.code=?;");
        return findByCode(sql.toString(),new StudentMapper(),code);
    }

    @Override
    public List<StudentModel> findAll() {
        StringBuilder sql=new StringBuilder("\n" +
                "SELECT \n" +
                "    student.*,\n" +
                "    liststudent.ClassCode AS ClassCode,\n" +
                "     academic.code as AcademicCode,\n" +
                "    academic.name as AcademicName,\n" +
                "    CEIL(TIMESTAMPDIFF(YEAR, student.createDate, NOW()) + 1) AS YearStudy\n" +
                "FROM \n" +
                "    student\n" +
                "INNER JOIN \n" +
                "    liststudent ON student.code = liststudent.StudentCode\n" +
                "INNER JOIN \n" +
                "    class ON liststudent.ClassCode = class.code\n" +
                "inner join listclass on class.code=listclass.ClassCode\n" +
                "inner join academic on listclass.AcademicCode=academic.code;");
//        if(pageable.getSorter()!=null&&!StringUtils.isNullOrEmpty(pageable.getSorter().getSortName())){
//            sql.append(" order by "+pageable.getSorter().getSortName()+" "+pageable.getSorter().getSortBy());
//        }
//        if(pageable.getOffset()!=null&&pageable.getLimit()!=null){
//            sql.append(" limit "+pageable.getOffset()+", "+pageable.getLimit());
//        }
        return query(sql.toString(),new StudentMapper());
    }

    @Override
    public Long update(StudentModel studentModel,boolean updateCode) {
        String sql="update student set code=?,fullname=?,sex=?,birthday=?,address=?,district=?,city=?,phone=?,email=?,image=?,linkFace=?,CCCD=?,createDate=?,createBy=?,modifiedDate=?,modifiedBy=? where id=?";
        Long id= update(sql,studentModel.getCode(),studentModel.getName(),studentModel.getSex(),studentModel.getBirthday(),studentModel.getAddress(),studentModel.getDistrict(),studentModel.getCity(),studentModel.getPhone(),studentModel.getEmail(),studentModel.getImage(),studentModel.getLinkFacebook(),studentModel.getCccd(),studentModel.getCreateDate(),studentModel.getCreateBy(),studentModel.getModifiedDate(),studentModel.getModifiedBy(),studentModel.getId());
        if(id!=-1L){
            if(updateCode){
                //update account,userroles if update student code
                //update list student if update student code
            }
        }else return -1L;
        return 1L;
    }

    @Override
    public void delete(Long id) {
        String sql="delete from student where id=?";
        update(sql,id);
        //delete Student in class
    }

    @Override
    public StudentModel findAllStudentByFacultyCode(String code) {
        String sql="select liststudent.ClassCode,student.code as StudentCode, student.fullname as fullname from faculty\n" +
                "inner join listacademic on faculty.code=listacademic.FacultyCode\n" +
                "inner join listclass on listacademic.AcademicCode=listclass.AcademicCode\n" +
                "inner join liststudent on listclass.ClassCode=liststudent.ClassCode\n" +
                "inner join student on liststudent.StudentCode=student.code\n" +
                "where faculty.code=? order by StudentCode asc";
        StudentModel studentModel=new StudentModel();
        studentModel.setList(query(sql,new StudentMapper(),code));
        return studentModel;
    }

    @Override
    public StudentModel findStudentInFaculty(String facultyCode,String studentCode) {
        String sql="select liststudent.ClassCode,student.* from faculty\n" +
                "inner join listacademic on faculty.code=listacademic.FacultyCode\n" +
                "inner join listclass on listacademic.AcademicCode=listclass.AcademicCode\n" +
                "inner join liststudent on listclass.ClassCode=liststudent.ClassCode\n" +
                "inner join student on liststudent.StudentCode=student.code\n" +
                "where faculty.code=? and student.code=? order by StudentCode asc;";
       StudentModel studentModel=new StudentModel();
         List<StudentModel>studentList=query(sql,new StudentMapper(),facultyCode,studentCode);
       if(!studentList.isEmpty())studentModel=studentList.get(0);
       else return null;
        FacultyModel facultyModel=new FacultyModel();
        facultyModel.setCode(facultyCode);
        studentModel.setFacultyModel(facultyModel);
        return studentModel;
    }
}
