package com.estimp.breakify_service.model.dto;

import com.estimp.breakify_service.model.Notification;

import java.util.List;

public class AppWithNotificationsDTO {
    public Long appId;
    public String appName;
    public List<Notification> notifications;

    public AppWithNotificationsDTO(Long appId, String appName, List<Notification> notifications) {
        this.appId = appId;
        this.appName = appName;
        this.notifications = notifications;
    }
}