package com.estimp.breakify_service.controller;

import com.estimp.breakify_service.model.Notification;
import com.estimp.breakify_service.model.dto.NotificationDTO;
import com.estimp.breakify_service.services.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> findAll() {
        return notificationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> findOne(@PathVariable Long id) {
        return notificationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody NotificationDTO notificationDto) {
        try {
            System.out.println("Received notification: " + notificationDto.getTitle() + "; from App: " + notificationDto.getPackageName() + " and User: " +  notificationDto.getUsername());
            return ResponseEntity.ok(notificationService.save(notificationDto));
        }
        catch (EntityNotFoundException e) {
            System.out.println("But throw an exception due to " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
