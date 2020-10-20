package com.lfg.spring.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lfg.spring.model.Group;
import com.lfg.spring.model.Member;
import com.lfg.spring.model.User;
import com.lfg.spring.model.DTO.GroupDto;
import com.lfg.spring.repository.GroupRepository;
import com.lfg.spring.repository.MemberRepository;
import com.lfg.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MemberService memberService;

    public List<Group> getAll(){
        
        return groupRepository.findAll();
    }

    public List<Group> getByTopic(String topic){

        return groupRepository.findByTopic(topic);
    }

    public Group create(GroupDto groupDto) {

        Group group = new Group();
        group.setDescription(groupDto.getDescription());
        group.setCreatedAt(new Date());
        group.setDisabled(false);
        group.setMembers(new LinkedList<>());
        group.setName(groupDto.getName());
        group.setPosts(new LinkedList<>());
        group.setTopic(groupDto.getTopic());
        group.setType(groupDto.getType());

        group = groupRepository.save(group);

        memberService.createAdminIn(group);

        return group;
    }

    public Group getReference(Long groupId){

        return groupRepository.getOne(groupId);
    }

}
