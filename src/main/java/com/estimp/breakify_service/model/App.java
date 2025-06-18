package com.estimp.breakify_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private boolean published;

    @Column(name = "package_name")
    private String packageName;

    private String image;
}
