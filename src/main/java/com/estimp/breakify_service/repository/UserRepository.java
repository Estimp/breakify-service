package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }