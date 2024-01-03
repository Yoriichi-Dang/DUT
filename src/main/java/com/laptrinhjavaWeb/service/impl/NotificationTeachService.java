package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.dao.INotificationTeachDAO;
import com.laptrinhjavaWeb.model.NotificationTeachModel;
import com.laptrinhjavaWeb.service.INotificationTeachService;

import javax.inject.Inject;
import java.util.List;

public class NotificationTeachService implements INotificationTeachService {
@Inject
private INotificationTeachDAO notificationTeachDAO;
    @Override
    public List<NotificationTeachModel> findAll() {
        return notificationTeachDAO.findAll();
    }
}
