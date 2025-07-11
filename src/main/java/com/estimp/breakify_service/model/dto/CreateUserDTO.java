package com.estimp.breakify_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserDTO {
    private String username;

    private String password;

    private String image;

    private String session;
}
