package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.*;
import com.estimp.breakify_service.model.dto.AppWithNotificationsDTO;
import com.estimp.breakify_service.model.dto.CreateAppDTO;
import com.estimp.breakify_service.model.dto.NotificationsInAppDTO;
import com.estimp.breakify_service.model.dto.mapper.AppMapper;
import com.estimp.breakify_service.repository.AppRepository;
import com.estimp.breakify_service.repository.UserAppRepository;
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
    private final UserAppRepository userAppRepository;

    public AppService(AppRepository appRepository, NotificationQueryService notificationQueryService, UserService userService, UserAppRepository userAppRepository) {
        this.appRepository = appRepository;
        this.notificationQueryService = notificationQueryService;
        this.userService = userService;
        this.userAppRepository = userAppRepository;
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

        UserApp userApp = userAppRepository.findByUserAndApp_PackageName(user, packageName)
                .orElseThrow(() -> new EntityNotFoundException("App not found (findByUsernameAndPackageName)"));

        App app = userApp.getApp();

        return getAppWithRecentNotifications(app.getId());
    }

    public App getAppByUserAndPackageName(User user, String packageName) {
        UserApp userApp = userAppRepository.findByUserAndApp_PackageName(user, packageName)
               .orElseThrow(() -> new EntityNotFoundException("App not found (getAppByUserAndPackageName)"));

        return userApp.getApp();
    }

    public App save(CreateAppDTO createAppDTO) {
        App app = AppMapper.toEntity(createAppDTO);

        if (existsByPackageName(app.getPackageName())) {
            app = appRepository.findByPackageName(createAppDTO.getPackageName())
                    .orElseThrow(() -> new EntityNotFoundException("App not found (save)"));
        }

        User user = userService.findByUsername(createAppDTO.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        app = appRepository.save(app);

        UserApp userApp = new UserApp();
        userApp.setUser(user);
        userApp.setApp(app);
        userApp.setId(new UserAppId(user.getId(), app.getId()));
        userApp.setPublished(createAppDTO.isPublished());

        userService.save(userApp);

        return app;
    }

    public AppWithNotificationsDTO getAppWithRecentNotifications(Long appId, int hours) {
        if (hours < 1) {
            throw new IllegalArgumentException("The hours must be greater than zero");
        }
        ZonedDateTime cutoff = ZonedDateTime.now().minusHours(hours);

        App app = appRepository.findById(appId)
                .orElseThrow(() -> new EntityNotFoundException("App not found (getAppWithRecentNotifications)"));

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

        int updated = userAppRepository.updatePublishStatusByUserAndPackageName(publishStatus, packageName, user);

        if (updated == 0) {
            throw new EntityNotFoundException("App not found for given user and package");
        }
    }

    private AppWithNotificationsDTO getAppWithRecentNotifications(Long appId) {
        return getAppWithRecentNotifications(appId, 24);
    }
}
