package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.Notification;
import com.estimp.breakify_service.model.dto.AppWithNotificationsDTO;
import com.estimp.breakify_service.repository.AppRepository;
import com.estimp.breakify_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    private final AppRepository appRepository;
    private final NotificationRepository notificationRepository;

    public AppService(AppRepository appRepository, NotificationRepository notificationRepository) {
        this.appRepository = appRepository;
        this.notificationRepository = notificationRepository;
    }

    public List<App> findAll() {
        return appRepository.findAll();
    }

    public Optional<App> findById(Long id) {
        return appRepository.findById(id);
    }

    public App save(App app) {
        return appRepository.save(app);
    }

    public AppWithNotificationsDTO getAppWithRecentNotifications(Long appId, ZonedDateTime cutoff) {
        Optional<App> appOptional = appRepository.findById(appId);
        if (appOptional.isEmpty()) return null;

        List<Notification> recentNotifications = notificationRepository.findRecentNotificationsByAppId(appId, cutoff);
        App app = appOptional.get();

        return new AppWithNotificationsDTO(app.getId(), app.getName(), recentNotifications);
    }
}
