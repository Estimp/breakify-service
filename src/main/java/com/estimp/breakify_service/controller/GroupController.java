package com.estimp.breakify_service.controller;

import com.estimp.breakify_service.model.Group;
import com.estimp.breakify_service.model.dto.CreateGroupDTO;
import com.estimp.breakify_service.model.dto.ResponseCreateGroupDTO;
import com.estimp.breakify_service.services.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(groupService.save(groupDto));
    }
}
