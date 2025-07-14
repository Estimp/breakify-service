package com.estimp.breakify_service.repository;

import com.estimp.breakify_service.model.Group;
import com.estimp.breakify_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Set<Group> findByUser(User user);

    Group findByUserAndName(User user, String name);

    @Modifying
    @Query("""
       UPDATE Group g
       SET g.published = :publishStatus
       WHERE g.name = :groupName AND :user = g.user
    """)
    int updatePublishStatusByUserAndGroupName(boolean publishStatus, String groupName, User user);
}