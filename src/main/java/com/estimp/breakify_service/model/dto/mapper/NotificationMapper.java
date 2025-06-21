package com.estimp.breakify_service.model.dto.mapper;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.Notification;
import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.model.dto.NotificationDTO;

public class NotificationMapper {
    public static Notification toEntity(NotificationDTO dto, App app, User user) {
        Notification notification = new Notification();
        notification.setTitle(dto.getTitle());
        notification.setText(dto.getText());
        notification.setImage(dto.getImage());
        notification.setRead(dto.isRead());
        notification.setTimestamp(dto.getTimestamp());
        notification.setApp(app);
        notification.setUser(user);
        return notification;
    }
}
