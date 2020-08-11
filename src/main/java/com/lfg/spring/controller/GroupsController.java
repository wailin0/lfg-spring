package com.lfg.spring.controller;

import com.lfg.spring.model.Groups;
import com.lfg.spring.model.Members;
import com.lfg.spring.model.Users;
import com.lfg.spring.repository.GroupsRepository;
import com.lfg.spring.repository.MembersRepository;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
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
        Members members = new Members();
        members.setGroupId(group.getId());
        members.setRole("ADMIN");
        members.setUserId(userId);
        membersRepository.save(members);
    }

}
