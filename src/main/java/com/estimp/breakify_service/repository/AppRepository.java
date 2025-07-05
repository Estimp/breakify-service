package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {
    Optional<App> findByUsersContainingAndPackageName(User user, String packageName);
    boolean existsByPackageName(String packageName);
    Optional<App> findByPackageName(String packageName);
    @Modifying
    @Query("""
       UPDATE App a
       SET a.published = :publishStatus
       WHERE a.packageName = :packageName AND :user MEMBER OF a.users
    """)
    int updatePublishStatusByUserAndPackageName(boolean publishStatus, String packageName, User user);
}
