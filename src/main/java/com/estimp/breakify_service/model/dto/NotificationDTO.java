package com.estimp.breakify_service.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class NotificationDTO {
    private String title;

    private String text;

    private String image;

    private boolean isRead;

    private ZonedDateTime timestamp;

    private Long userId;

    private Long appId;
}
