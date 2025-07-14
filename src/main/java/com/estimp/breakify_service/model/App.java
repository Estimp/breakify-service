package com.estimp.breakify_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app", indexes = {
        @Index(name = "idx_unique_package_name", columnList = "package_name", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "package_name")
    private String packageName;

    private String image;

    @OneToMany(mappedBy = "app")
    private Set<UserApp> userApps = new HashSet<>();
}
