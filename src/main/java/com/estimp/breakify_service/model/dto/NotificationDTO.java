package com.estimp.breakify_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class NotificationDTO {
    private String title;

    private String text;

    private String image;

    private boolean isRead;

    private ZonedDateTime timestamp;

    private String username;

    private String packageName;
}
