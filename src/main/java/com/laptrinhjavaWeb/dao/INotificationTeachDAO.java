package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.NotificationTeachModel;

import java.time.LocalDate;
import java.util.List;

public interface INotificationTeachDAO {
    List<NotificationTeachModel>findAll();
    NotificationTeachModel findByModel(String ClassCourseCode, LocalDate date);
}
