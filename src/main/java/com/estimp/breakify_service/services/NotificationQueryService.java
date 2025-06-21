package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.Notification;
import com.estimp.breakify_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> findRecentNotificationsByAppId(Long appId, ZonedDateTime cutoff) {
        return notificationRepository.findRecentNotificationsByAppId(appId, cutoff);
    }
}
