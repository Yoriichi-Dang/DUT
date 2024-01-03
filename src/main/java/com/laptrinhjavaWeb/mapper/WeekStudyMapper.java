package com.laptrinhjavaWeb.mapper;

import com.laptrinhjavaWeb.model.WeekStudyModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class WeekStudyMapper implements RowMapper<WeekStudyModel> {

    @Override
    public WeekStudyModel mapRow(ResultSet resultSet) {
        WeekStudyModel weekStudyModel=new WeekStudyModel();
        try{
            WeekStudyModel startWeekStudy=new WeekStudyModel();
            startWeekStudy.setDate(resultSet.getDate("startWeek").toLocalDate());
            System.out.println(startWeekStudy.getDate());
            WeekStudyModel endWeekStudy=new WeekStudyModel();
            endWeekStudy.setDate(resultSet.getDate("endWeek").toLocalDate());
            System.out.println(endWeekStudy.getDate());
            weekStudyModel.setEndWeekStudy(endWeekStudy);
            weekStudyModel.setStartWeekStudy(startWeekStudy);
        }catch (Exception e){
            e.printStackTrace();
        }
        return weekStudyModel;
    }
}
