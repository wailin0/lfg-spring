package com.lfg.spring.service;

import com.lfg.spring.model.Groups;
import com.lfg.spring.model.Members;
import com.lfg.spring.model.Users;
import com.lfg.spring.repository.GroupsRepository;
import com.lfg.spring.repository.MembersRepository;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void createGroup(String username, Groups group) {

        Users user = usersRepository.findByUsername(username);

        groupsRepository.save(group);
        Members members = new Members();  //make first member admin
        members.setGroups(group);
        members.setRole("ADMIN");
        members.setUsers(user);
        membersRepository.save(members);
    }
}
