package com.estimp.breakify_service.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
public class NotificationResponseDTO {
    private Long id;

    private String title;

    private String text;

    private String image;

    private boolean isRead;

    private ZonedDateTime timestamp;

    private Long userId;

    private String username;

    private Long appId;

    private String packageName;
}
