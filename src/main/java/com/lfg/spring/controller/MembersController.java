package com.lfg.spring.controller;

import com.lfg.spring.model.Members;
import com.lfg.spring.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MembersController {

    @Autowired
    private MembersRepository membersRepository;


    @GetMapping("/group/{groupId}/members")
    public List<Members> getMembersByGroupId(@PathVariable Long groupId){
        return membersRepository.findByGroupId(groupId);
    }


    @PostMapping("/group/{groupId}/user/{userId}")
    public void joinGroup (@PathVariable Long groupId, @PathVariable Long userId) {
        Members members = new Members();
        members.setGroupId(groupId);
        members.setUserId(userId);
        members.setRole("MEMBER");
        membersRepository.save(members);
    }

    @DeleteMapping("/group/{groupId}/user/{userId}")
    public void leaveGroup (@PathVariable Long groupId, @PathVariable Long userId) {
        membersRepository.deleteByGroupIdAndUserId(groupId, userId);
    }




}
