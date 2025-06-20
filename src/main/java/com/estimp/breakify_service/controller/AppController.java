package com.estimp.breakify_service.controller;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.dto.AppWithNotificationsDTO;
import com.estimp.breakify_service.model.dto.CreateAppDTO;
import com.estimp.breakify_service.services.AppService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apps")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping
    public List<App> findAll() {
        return appService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<App> findOne(@PathVariable Long id) {
        return appService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}/packageName/{packageName}")
    public ResponseEntity<?> findByUserAndPackageName(
            @PathVariable String username,
            @PathVariable String packageName
    ) {
        try {
            AppWithNotificationsDTO result = appService.findByUsernameAndPackageName(username, packageName);
            return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<App> save(@RequestBody CreateAppDTO app) {
        return ResponseEntity.ok(appService.save(app));
    }

    @GetMapping("/{id}/recent-notifications")
    public ResponseEntity<?> getAppWithRecentNotifications(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "24") int hours
    ) {
        try {
            AppWithNotificationsDTO result = appService.getAppWithRecentNotifications(id, hours);
            return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/packageName/{packageName}")
    public boolean existsPackageName(@PathVariable String packageName) {
        try {
            return appService.existsByPackageName(packageName);
        } catch (Exception e) {
            return false;
        }
    }
}
