package com.lfg.spring.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.lfg.spring.model.Group;
import com.lfg.spring.model.DTO.GroupDto;
import com.lfg.spring.model.User;
import com.lfg.spring.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MemberService memberService;


    @Autowired
    private AuthService authService;


    public List<Group> getAll(){
        
        return groupRepository.findAll();
    }

    public List<Group> getByTopic(String topic){

        return groupRepository.findByTopic(topic);
    }

    public Group create(GroupDto groupDto) {

        User user = authService.getCurrentLoggedInUser();
        Group group = new Group();
        group.setUser(user);
        group.setDescription(groupDto.getDescription());
        group.setCreatedAt(new Date());
        group.setDisabled(false);
        group.setName(groupDto.getName());
        group.setTopic(groupDto.getTopic());
        group.setType(groupDto.getType());

        group = groupRepository.save(group);

        memberService.createAdminIn(group);

        return group;
    }

    public Group getReference(Long groupId){

        return groupRepository.getOne(groupId);
    }

    public List<Group> getGroupsOfUser() {
        User user = authService.getCurrentLoggedInUser();
        return groupRepository.findByUserUserId(user.getUserId());
    }

    public Optional<Group> findByName(String groupName) {
        return groupRepository.findByName(groupName);
    }
}
