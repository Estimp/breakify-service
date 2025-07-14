package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {

    Optional<UserApp> findByUserAndApp_PackageName(User user, String packageName);

    @Modifying
    @Query("""
        UPDATE UserApp ua
        SET ua.published = :publishStatus
        WHERE ua.app.packageName = :packageName AND ua.user = :user
    """)
    int updatePublishStatusByUserAndPackageName(boolean publishStatus, String packageName, User user);
}