package com.lfg.spring.controller;

import com.lfg.spring.model.Group;
import com.lfg.spring.model.DTO.GroupDto;
import com.lfg.spring.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private GroupService groupService;


    //find groups that user is in
    @GetMapping("/user/group/user")
    public List<Group> getGroupsFromUser () {

        return groupService.getGroupsOfUser();
    }

    //get group info by name
    @GetMapping("/group/{groupName}")
    public Optional<Group> findGroupByName(@PathVariable String groupName) {
        return groupService.findByName(groupName);
    }

    @GetMapping("/group")
    public ResponseEntity<List<Group>> getAll() {

        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity<Group> create(@RequestBody GroupDto groupDto){
        return new ResponseEntity<>(groupService.create(groupDto), HttpStatus.OK);
    }

    @GetMapping("/group/topic/{topic}")
    public ResponseEntity<List<Group>> getGroupByTopic(@PathVariable String topic) {

        return new ResponseEntity<>(groupService.getByTopic(topic), HttpStatus.OK);
    }


    //@PutMapping("/group/{groupId}")
    //@DeleteMapping("/group/{groupId}")

}
