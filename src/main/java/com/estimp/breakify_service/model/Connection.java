package com.estimp.breakify_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "connection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Connection {
    @Id
    private Long id = 1L;

    private boolean mqttEnabled = true;
}
