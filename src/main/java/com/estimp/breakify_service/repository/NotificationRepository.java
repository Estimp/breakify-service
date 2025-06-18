package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.app.id = :appId AND n.timestamp >= :cutoff")
    List<Notification> findRecentNotificationsByAppId(@Param("appId") Long appId, @Param("cutoff") ZonedDateTime cutoff);
}