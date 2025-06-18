package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> { }