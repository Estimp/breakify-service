package com.estimp.breakify_service.model.dto.mapper;

import com.estimp.breakify_service.model.Notification;
import com.estimp.breakify_service.model.dto.NotificationResponseDTO;

public class NotificationResponseMapper {
    public static NotificationResponseDTO toDto(Notification notification) {
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO();
        notificationResponseDTO.setId(notification.getId());
        notificationResponseDTO.setTitle(notification.getTitle());
        notificationResponseDTO.setText(notification.getText());
        notificationResponseDTO.setImage(notification.getImage());
        notificationResponseDTO.setRead(notification.isRead());
        notificationResponseDTO.setTimestamp(notification.getTimestamp());
        notificationResponseDTO.setUserId(notification.getUser().getId());
        notificationResponseDTO.setUsername(notification.getUser().getUsername());
        notificationResponseDTO.setAppId(notification.getApp().getId());
        notificationResponseDTO.setPackageName(notification.getApp().getPackageName());
        return notificationResponseDTO;
    }
}
