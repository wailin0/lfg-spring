package com.lfg.spring.controller;

import com.lfg.spring.model.Groups;
import com.lfg.spring.model.Members;
import com.lfg.spring.repository.GroupsRepository;
import com.lfg.spring.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class GroupsController {

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private MembersRepository membersRepository;

    @GetMapping("/group")
    public List<Groups> getAllGroup() {
        return groupsRepository.findAll();
    }

    @GetMapping("/group/{topic}")
    public List<Groups> getGroupByTopic(@PathVariable String topic) {
        return groupsRepository.findByTopic(topic);
    }


    @PostMapping("/group/{userId}")
    public void createGroup(@PathVariable Long userId, @RequestBody Groups group) {
        groupsRepository.save(group);
        Members members = new Members();  //make first member admin
        members.setGroupId(group.getId());
        members.setRole("ADMIN");
        members.setUserId(userId);
        membersRepository.save(members);
    }

}
