package com.estimp.breakify_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AppWithNotificationsDTO {
    private Long appId;
    private String appName;
    private List<NotificationsInAppDTO> notifications;
}