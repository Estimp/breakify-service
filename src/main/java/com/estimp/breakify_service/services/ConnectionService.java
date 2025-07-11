package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.Connection;
import com.estimp.breakify_service.repository.ConnectionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConnectionService {
    private final ConnectionRepository connectionRepository;

    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    @PostConstruct
    public void init() {
        if (!connectionRepository.existsById(1L)) {
            connectionRepository.save(new Connection(1L, true));
        }
    }

    @Transactional
    public boolean updateMqttEnabled(boolean enabled) {
        Connection connection = connectionRepository.getReferenceById(1L);

        connection.setMqttEnabled(enabled);
        connectionRepository.save(connection);
        return enabled;
    }

    public boolean isMqttEnabled() {
        Connection connection = connectionRepository.getReferenceById(1L);
        return connection.isMqttEnabled();
    }
}
