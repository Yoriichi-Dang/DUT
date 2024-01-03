package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.ICourseDAO;
import com.laptrinhjavaWeb.dao.ICourseDependentDAO;
import com.laptrinhjavaWeb.mapper.CourseDependentMapper;
import com.laptrinhjavaWeb.model.CourseDependentModel;
import com.laptrinhjavaWeb.model.CourseModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CourseDependentDAO extends AbstractDAO<CourseDependentModel>implements ICourseDependentDAO {
    @Inject
    private ICourseDAO courseDAO;
    @Override
    public CourseDependentModel findAllCourseInEducationByStudentCode(String code) {
        String sql="SELECT\n" +
                "    Subquery.CourseCode,\n" +
                "    course.name AS CourseName,\n" +
                "    course.id AS id,\n" +
                "    course.numberCredit,\n" +
                "    course.pointStudy,\n" +
                "    MAX(CASE WHEN Subquery.Type = 'courseStudyBefore' THEN Subquery.DependentList END) AS DependentList_courseStudyBefore,\n" +
                "    MAX(CASE WHEN Subquery.Type = 'courseStudyTogether' THEN Subquery.DependentList END) AS DependentList_courseStudyTogether\n" +
                "FROM (\n" +
                "    SELECT\n" +
                "        ec.CourseCode,\n" +
                "        lc.type AS Type,\n" +
                "        GROUP_CONCAT(DISTINCT lc.DependentCourseCode ORDER BY lc.DependentCourseCode SEPARATOR ',') AS DependentList\n" +
                "    FROM\n" +
                "        educationstudent es\n" +
                "        INNER JOIN educationcourse ec ON es.EducationCode = ec.EducationCode\n" +
                "        INNER JOIN course c ON ec.CourseCode = c.code\n" +
                "        LEFT JOIN listcourse lc ON c.code = lc.CourseCode\n" +
                "    WHERE\n" +
                "        es.StudentCode = ?\n" +
                "    GROUP BY\n" +
                "        ec.CourseCode, lc.type\n" +
                ") AS Subquery\n" +
                "JOIN course ON Subquery.CourseCode = course.code\n" +
                "GROUP BY Subquery.CourseCode, CourseName, course.numberCredit, course.pointStudy;\n";
        List<CourseDependentModel> courseDependentModelList=query(sql,new CourseDependentMapper(),code);
        CourseDependentModel courseDependentModel=new CourseDependentModel();
        for(int i=0;i<courseDependentModelList.size();i++){
            CourseDependentModel model=courseDependentModelList.get(i);
            String[]courseStudyBeforeCodeStr=model.getDependentList_courseStudyBeforeCodeStr();
            String[]courseStudyTogetherCodeStr=model.getDependentList_courseStudyTogetherCodeStr();
            List<CourseModel>list=new ArrayList<>();
            if(courseStudyBeforeCodeStr!=null){
                for(int j=0;j<courseStudyBeforeCodeStr.length;j++){
                    list.add(courseDAO.findByCode(courseStudyBeforeCodeStr[j].trim()));
                }
            }
            courseDependentModelList.get(i).setDependentList_courseStudyBeforeCode(list);
            list=new ArrayList<>();
            if(courseStudyTogetherCodeStr!=null&&list.isEmpty()){
                for(int j=0;j<courseStudyTogetherCodeStr.length;j++){
                    list.add(courseDAO.findByCode(courseStudyTogetherCodeStr[j].trim()));
                }
            }
            courseDependentModelList.get(i).setDependentList_courseStudyTogetherCode(list);
        }
        courseDependentModel.setList(courseDependentModelList);
        return courseDependentModel;
    }
}
