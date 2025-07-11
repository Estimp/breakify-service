package com.estimp.breakify_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_app")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserApp {

    @EmbeddedId
    private UserAppId id = new UserAppId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("appId")
    @JoinColumn(name = "app_id")
    private App app;

    @Column(name = "published")
    private boolean published;
}