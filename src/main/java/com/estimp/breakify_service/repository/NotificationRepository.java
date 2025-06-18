package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> { }