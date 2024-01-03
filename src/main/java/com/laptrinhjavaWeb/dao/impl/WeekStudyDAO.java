package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.IWeekStudyDAO;
import com.laptrinhjavaWeb.mapper.WeekStudyMapper;
import com.laptrinhjavaWeb.model.WeekStudyModel;

import java.util.List;

public class WeekStudyDAO extends AbstractDAO<WeekStudyModel> implements IWeekStudyDAO {

    @Override
    public WeekStudyModel findDateWeekDurationInYear(int startWeek, int endWeek, int year) {
        String sql="SELECT ws1.date AS startWeek, ws12.date AS endWeek\n" +
                "FROM weekstudy ws1\n" +
                "INNER JOIN weekstudy ws12 ON ws1.week = ? AND ws12.week = ?\n" +
                "WHERE YEAR(ws1.date) = ? AND YEAR(ws12.date) = ?;";
        List<WeekStudyModel>list=query(sql,new WeekStudyMapper(),startWeek,endWeek,year,year);
        if(list!=null&&!list.isEmpty()){
            WeekStudyModel weekStudyModel=list.get(0);
            weekStudyModel.getEndWeekStudy().setWeek(endWeek);
            weekStudyModel.getStartWeekStudy().setWeek(startWeek);
            return weekStudyModel;
        }
        return null;
    }
}
