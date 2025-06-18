package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.repository.AppRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    private final AppRepository appRepository;

    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public List<App> findAll() {
        return appRepository.findAll();
    }

    public Optional<App> findById(Long id) {
        return appRepository.findById(id);
    }

    public App save(App app) {
        return appRepository.save(app);
    }
}
