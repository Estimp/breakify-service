package com.estimp.breakify_service.model.dto.mapper;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.dto.CreateAppDTO;

public class AppMapper {
    public static App toEntity(CreateAppDTO appDTO) {
        App app = new App();
        app.setName(appDTO.getName());
        app.setPackageName(appDTO.getPackageName());
        app.setImage(appDTO.getImage());
        return app;
    }
}
