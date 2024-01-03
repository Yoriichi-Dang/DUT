package com.laptrinhjavaWeb.model;

import com.laptrinhjavaWeb.dao.impl.AbstractDAO;

import java.time.LocalDate;
import java.util.Date;

public class WeekStudyModel extends AbstractModel<WeekStudyModel> {
    private int week;
    private LocalDate date;
    private WeekStudyModel startWeekStudy;
    private WeekStudyModel endWeekStudy;

    public WeekStudyModel getStartWeekStudy() {
        return startWeekStudy;
    }

    public void setStartWeekStudy(WeekStudyModel startWeekStudy) {
        this.startWeekStudy = startWeekStudy;
    }

    public WeekStudyModel getEndWeekStudy() {
        return endWeekStudy;
    }

    public void setEndWeekStudy(WeekStudyModel endWeekStudy) {
        this.endWeekStudy = endWeekStudy;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
