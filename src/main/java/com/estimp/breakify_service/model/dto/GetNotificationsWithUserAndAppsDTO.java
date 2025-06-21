package com.estimp.breakify_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetNotificationsWithUserAndAppsDTO {
    private Long userId;
    private String username;
    private List<AppWithNotificationsDTO> apps;
}
