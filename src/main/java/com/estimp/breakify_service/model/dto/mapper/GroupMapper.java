package com.estimp.breakify_service.model.dto.mapper;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.Group;
import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.model.dto.CreateGroupDTO;
import com.estimp.breakify_service.model.dto.ResponseCreateGroupDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupMapper {
    public static Group toEntity(CreateGroupDTO groupDto, User user, Set<App> apps) {
        Group group = new Group();
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        group.setPublished(groupDto.isPublished());
        group.setUser(user);
        group.setApps(apps);
        return group;
    }

    public static ResponseCreateGroupDTO toDto(Group group) {
        ResponseCreateGroupDTO responseCreateGroupDto = new ResponseCreateGroupDTO();
        responseCreateGroupDto.setId(group.getId());
        responseCreateGroupDto.setName(group.getName());
        responseCreateGroupDto.setDescription(group.getDescription());
        responseCreateGroupDto.setPublished(group.isPublished());
        responseCreateGroupDto.setUsername(group.getUser().getUsername());
        responseCreateGroupDto.setAppPackageNames(group.getApps().stream().map(App::getPackageName).collect(Collectors.toSet()));
        return responseCreateGroupDto;
    }
}
