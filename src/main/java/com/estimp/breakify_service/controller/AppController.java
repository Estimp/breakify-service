package com.estimp.breakify_service.controller;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.dto.AppWithNotificationsDTO;
import com.estimp.breakify_service.services.AppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
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

    @PostMapping
    public ResponseEntity<App> save(@RequestBody App app) {
        return ResponseEntity.ok(appService.save(app));
    }

    @GetMapping("/{id}/recent-notifications")
    public ResponseEntity<?> getAppWithRecentNotifications(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "24") int hours
    ) {
        if (hours < 1) {
            return ResponseEntity.badRequest().body("The hours must be greater than zero");
        }
        ZonedDateTime cutoff = ZonedDateTime.now().minusHours(hours);
        AppWithNotificationsDTO result = appService.getAppWithRecentNotifications(id, cutoff);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
}
