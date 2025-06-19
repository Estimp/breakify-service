package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {
    Optional<App> findByUsersContainingAndPackageName(User user, String packageName);
}
