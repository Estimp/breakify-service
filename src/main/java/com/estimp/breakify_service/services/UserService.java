package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.model.UserApp;
import com.estimp.breakify_service.model.dto.CreateUserDTO;
import com.estimp.breakify_service.model.dto.mapper.UserMapper;
import com.estimp.breakify_service.model.enums.SessionType;
import com.estimp.breakify_service.repository.UserAppRepository;
import com.estimp.breakify_service.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserAppRepository userAppRepository;

    public UserService(UserRepository userRepository, UserAppRepository userAppRepository) {
        this.userRepository = userRepository;
        this.userAppRepository = userAppRepository;
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

    public void save(UserApp userApp) {
        userAppRepository.save(userApp);
    }

    public boolean logOut(String username) {
        User user = findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setSession(SessionType.LOGGED_OUT.toString());
        userRepository.save(user);
        return true;
    }
}
