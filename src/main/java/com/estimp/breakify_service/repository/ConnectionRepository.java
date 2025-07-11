package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> { }
