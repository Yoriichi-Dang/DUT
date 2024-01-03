package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.WeekStudyModel;

public interface IWeekStudyDAO {
    WeekStudyModel findDateWeekDurationInYear(int startWeek,int endWeek,int year);
}
