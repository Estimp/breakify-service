package com.estimp.breakify_service.services;

import com.estimp.breakify_service.model.App;
import com.estimp.breakify_service.model.Group;
import com.estimp.breakify_service.model.User;
import com.estimp.breakify_service.model.dto.CreateGroupDTO;
import com.estimp.breakify_service.model.dto.GetGroupDTO;
import com.estimp.breakify_service.model.dto.ResponseCreateGroupDTO;
import com.estimp.breakify_service.model.dto.mapper.GroupMapper;
import com.estimp.breakify_service.repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;
    private final AppService appService;

    public GroupService(GroupRepository groupRepository, UserService userService, AppService appService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.appService = appService;
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    public ResponseCreateGroupDTO save(CreateGroupDTO groupDto) {
        User user = userService.findByUsername(groupDto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Set<App> apps = new HashSet<>();
        for (String packageName : groupDto.getAppPackageNames()) {
            try {
                App app = appService.getAppByUserAndPackageName(user, packageName);
                apps.add(app);
            }
            catch (EntityNotFoundException e) {
                System.out.println("App not found, getting next app: " + e.getMessage());
            }
        }

        Group group = GroupMapper.toEntity(groupDto, user, apps);

        Group groupFromDb = groupRepository.save(group);

        return GroupMapper.toDto(groupFromDb);
    }

    public Set<GetGroupDTO> findByUsername(String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Set<Group> groups = groupRepository.findByUser(user);

        Set<GetGroupDTO> groupsDto = new TreeSet<>(Comparator.comparingLong(GetGroupDTO::getId));

        for (Group group : groups) {
            groupsDto.add(GroupMapper.toResponseDto(group));
        }

        return groupsDto;
    }

    public GetGroupDTO findOneByUsernameAndGroupName(String username, String groupName) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Group group = groupRepository.findByUserAndName(user, groupName);

        return GroupMapper.toResponseDto(group);
    }
}
