package com.estimp.breakify_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAppDTO {
    private String name;

    private boolean published;

    private String packageName;

    private String image;

    private String username;
}
