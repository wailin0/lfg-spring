package com.lfg.spring.service;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Groups;
import com.lfg.spring.model.Members;
import com.lfg.spring.model.Users;
import com.lfg.spring.repository.GroupsRepository;
import com.lfg.spring.repository.MembersRepository;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembersService {

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private GroupsRepository groupsRepository;

    public List<Members> getMemberList(Long groupId) {
        return membersRepository.findByGroups_Id(groupId);
    }

    public void joinGroup(String username, Long groupId){
        Users user = usersRepository.findByUsername(username);
        Groups group = groupsRepository.findById(groupId).orElse(null);
        Members members = new Members();
        members.setGroups(group);
        members.setUsers(user);
        members.setRole("MEMBER");
        membersRepository.save(members);
    }

    public void leaveGroup(String username, Long groupId) {
        Users user = usersRepository.findByUsername(username);
        membersRepository.deleteByGroups_IdAndUsers_Id(groupId, user.getId());
    }

    
}
