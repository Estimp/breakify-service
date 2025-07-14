package com.estimp.breakify_service.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class ResponseCreateGroupDTO {
    private Long id;

    private String name;

    private String description;

    private boolean published;

    private String username;

    private Set<String> appPackageNames;
}