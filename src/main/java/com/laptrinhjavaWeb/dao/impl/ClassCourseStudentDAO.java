package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.IClassCourseStudentDAO;
import com.laptrinhjavaWeb.mapper.ClassCourseStudentMapper;
import com.laptrinhjavaWeb.model.ClassCourseStudentModel;

import java.util.List;

public class ClassCourseStudentDAO extends  AbstractDAO<ClassCourseStudentModel> implements IClassCourseStudentDAO {
    @Override
    public ClassCourseStudentModel findHistoryRegisterByCourseCode(String code) {
        String sql="select historyregistercourse.* \n" +
                "from listclasscourse \n" +
                "inner join historyregistercourse  on listclasscourse.ClassCourseCode=historyregistercourse.ClassCourseCode\n" +
                "where listclasscourse.CourseCode=?";
        return findByCode(sql,new ClassCourseStudentMapper(),code);
    }

    @Override
    public ClassCourseStudentModel findResultStudy(String CourseCode, String studentCode) {
       String sql="select resultstudycourse.* \n" +
               "from listclasscourse \n" +
               "inner join resultstudycourse  on listclasscourse.ClassCourseCode=resultstudycourse.ClassCourseCode\n" +
               "where listclasscourse.CourseCode=? and resultstudycourse.StudentCode=?;";
       List<ClassCourseStudentModel> list=query(sql,new ClassCourseStudentMapper(),CourseCode,studentCode);
       if(list.isEmpty())return null;
       return list.get(0);
    }
}
