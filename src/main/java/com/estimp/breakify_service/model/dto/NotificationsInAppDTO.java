package com.estimp.breakify_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class NotificationsInAppDTO {
    private Long id;
    private String title;
    private String text;
    private ZonedDateTime timestamp;
}
