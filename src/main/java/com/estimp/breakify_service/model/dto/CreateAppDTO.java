package com.estimp.breakify_service.model.dto;

import lombok.Getter;

@Getter
public class CreateAppDTO {
    private String name;

    private boolean published;

    private String packageName;

    private String image;

    private String username;
}
