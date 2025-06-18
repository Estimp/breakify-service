package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, Long> { }
