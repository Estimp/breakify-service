package com.estimp.breakify_service.model.dto.mapper;

import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.model.dto.CreateUserDTO;

public class UserMapper {
    public static User toEntity(CreateUserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setImage(userDTO.getImage());
        user.setSession(userDTO.getSession());
        return user;
    }
}
