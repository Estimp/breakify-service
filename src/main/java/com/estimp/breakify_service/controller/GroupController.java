package com.estimp.breakify_service.controller;

import com.estimp.breakify_service.model.Group;
import com.estimp.breakify_service.model.dto.CreateGroupDTO;
import com.estimp.breakify_service.model.dto.GetGroupDTO;
import com.estimp.breakify_service.model.dto.ResponseCreateGroupDTO;
import com.estimp.breakify_service.services.GroupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> findOne(@PathVariable Long id) {
        return groupService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResponseCreateGroupDTO> save(@RequestBody CreateGroupDTO groupDto) {
        try {
            return ResponseEntity.ok(groupService.save(groupDto));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Set<GetGroupDTO>> findByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(groupService.findByUsername(username));
        }
        catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage() + ". " + e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/username/{username}/groupName/{groupName}")
    public ResponseEntity<GetGroupDTO> findOneByUsernameAndName(@PathVariable String username, @PathVariable String groupName) {
        try {
            return ResponseEntity.ok(groupService.findOneByUsernameAndGroupName(username, groupName));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/username/{username}/groupName/{groupName}")
    public ResponseEntity<Void> modifyPublishStatus(
            @PathVariable String username,
            @PathVariable String groupName,
            @RequestParam boolean publishStatus
    ) {
        try {
            groupService.changePublishStatus(publishStatus, username, groupName);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
