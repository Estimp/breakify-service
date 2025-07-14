package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {

    boolean existsByPackageName(String packageName);

    Optional<App> findByPackageName(String packageName);
}
