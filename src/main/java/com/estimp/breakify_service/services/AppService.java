package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.Notification;
import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.model.dto.AppWithNotificationsDTO;
import com.estimp.breakify_service.model.dto.CreateAppDTO;
import com.estimp.breakify_service.model.dto.NotificationsInAppDTO;
import com.estimp.breakify_service.model.dto.mapper.AppMapper;
import com.estimp.breakify_service.repository.AppRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    private final AppRepository appRepository;
    private final NotificationQueryService notificationQueryService;
    private final UserService userService;

    public AppService(AppRepository appRepository, NotificationQueryService notificationQueryService, UserService userService) {
        this.appRepository = appRepository;
        this.notificationQueryService = notificationQueryService;
        this.userService = userService;
    }

    public List<App> findAll() {
        return appRepository.findAll();
    }

    public Optional<App> findById(Long id) {
        return appRepository.findById(id);
    }

    public AppWithNotificationsDTO findByUsernameAndPackageName(String username, String packageName) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        App app = appRepository.findByUsersContainingAndPackageName(user, packageName)
                .orElseThrow(() -> new EntityNotFoundException("App not found"));

        return getAppWithRecentNotifications(app.getId());
    }

    public App getAppByUserAndPackageName(User user, String packageName) {
        return appRepository.findByUsersContainingAndPackageName(user, packageName)
                .orElseThrow(() -> new EntityNotFoundException("App not found"));
    }

    public App save(CreateAppDTO createAppDTO) {
        App app = AppMapper.toEntity(createAppDTO);

        if (existsByPackageName(app.getPackageName())) {
            app = appRepository.findByPackageName(createAppDTO.getPackageName())
                    .orElseThrow(() -> new EntityNotFoundException("App not found"));
        }

        User user = userService.findByUsername(createAppDTO.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        app.getUsers().add(user);
        user.getApps().add(app);

        return appRepository.save(app);
    }

    public AppWithNotificationsDTO getAppWithRecentNotifications(Long appId, int hours) {
        if (hours < 1) {
            throw new IllegalArgumentException("The hours must be greater than zero");
        }
        ZonedDateTime cutoff = ZonedDateTime.now().minusHours(hours);

        App app = appRepository.findById(appId)
                .orElseThrow(() -> new EntityNotFoundException("App not found"));

        List<Notification> recentNotifications = notificationQueryService.findRecentNotificationsByAppId(appId, cutoff);

        List<NotificationsInAppDTO> notificationDTOs = recentNotifications.stream()
                .map(n -> new NotificationsInAppDTO(n.getId(), n.getTitle(), n.getText(), n.getTimestamp()))
                .toList();

        return new AppWithNotificationsDTO(app.getId(), app.getName(), notificationDTOs);
    }

    public boolean existsByPackageName(String packageName) {
        return appRepository.existsByPackageName(packageName);
    }

    @Transactional
    public void changePublishStatus(boolean publishStatus, String username, String packageName) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        int updated = appRepository.updatePublishStatusByUserAndPackageName(publishStatus, packageName, user);

        if (updated == 0) {
            throw new EntityNotFoundException("App not found for given user and package");
        }
    }

    private AppWithNotificationsDTO getAppWithRecentNotifications(Long appId) {
        return getAppWithRecentNotifications(appId, 24);
    }
}
