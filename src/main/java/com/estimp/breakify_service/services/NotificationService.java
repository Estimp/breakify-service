package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.Notification;
import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.model.dto.AppWithNotificationsDTO;
import com.estimp.breakify_service.model.dto.GetNotificationsWithUserAndAppsDTO;
import com.estimp.breakify_service.model.dto.NotificationDTO;
import com.estimp.breakify_service.model.dto.mapper.NotificationMapper;
import com.estimp.breakify_service.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;
    private final AppService appService;

    public NotificationService(NotificationRepository notificationRepository,
                               UserService userService,
                               AppService appService) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.appService = appService;
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    public Notification save(NotificationDTO notificationDto) {
        User user = userService.findByUsername(notificationDto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        App app = appService.getAppByUserAndPackageName(user, notificationDto.getPackageName());

        Notification notification = NotificationMapper.toEntity(notificationDto, app, user);
        return notificationRepository.save(notification);
    }

    public GetNotificationsWithUserAndAppsDTO findByUsername(String username, int hours, boolean showOnlyAppsWithNotifications) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Set<App> apps = user.getApps();
        List<AppWithNotificationsDTO> appsWithNotifications = new LinkedList<>();

        apps.forEach(app -> {
            AppWithNotificationsDTO appWithNotifications = appService.getAppWithRecentNotifications(app.getId(), hours);
            if (showOnlyAppsWithNotifications) {
                if (!appWithNotifications.getNotifications().isEmpty()) {
                    appsWithNotifications.add(appWithNotifications);
                }
            }
            else {
                appsWithNotifications.add(appWithNotifications);
            }
        });

        return new GetNotificationsWithUserAndAppsDTO(user.getId(), user.getUsername(), appsWithNotifications);
    }
}
