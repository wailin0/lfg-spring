package com.lfg.spring.controller;

import com.lfg.spring.model.Group;
import com.lfg.spring.model.DTO.GroupDto;
import com.lfg.spring.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAll() {

        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/groups")
    public ResponseEntity<Group> create(@RequestBody GroupDto groupDto){

        return new ResponseEntity<>(groupService.create(groupDto), HttpStatus.OK);
    }

    @GetMapping("/group/{topic}")
    public ResponseEntity<List<Group>> getGroupByTopic(@PathVariable String topic) {

        return new ResponseEntity<>(groupService.getByTopic(topic), HttpStatus.OK);
    }

}
