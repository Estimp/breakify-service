package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.Group;
import com.estimp.breakify_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Set<Group> findByUser(User user);

    Group findByUserAndName(User user, String name);
}