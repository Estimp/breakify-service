package com.estimp.breakify_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class CreateGroupDTO {
    private String name;

    private String description;

    private boolean published;

    private String username;

    private Set<String> appPackageNames;
}