package com.estimp.breakify_service.controller;

import com.estimp.breakify_service.services.ConnectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/connection")
public class ConnectionController {
    private ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PutMapping("/{activate}")
    public ResponseEntity<Boolean> connectionToMqtt(@PathVariable boolean activate) {
        try {
            boolean updated = connectionService.updateMqttEnabled(activate);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
