package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.model.dto.CreateUserDTO;
import com.estimp.breakify_service.model.dto.mapper.UserMapper;
import com.estimp.breakify_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(CreateUserDTO createUserDTO) {
        User user = UserMapper.toEntity(createUserDTO);
        return userRepository.save(user);
    }
}
