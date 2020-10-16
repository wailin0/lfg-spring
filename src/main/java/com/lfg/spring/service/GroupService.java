package com.lfg.spring.service;

import com.lfg.spring.model.Group;
import com.lfg.spring.model.Member;
import com.lfg.spring.model.User;
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
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    /*public void createGroup(String username, Group group) {

        User user = userRepository.findByUsername(username);

        groupRepository.save(group);
        Member member = new Member();  //make first member admin
        member.setGroup(group);
        member.setRole("ADMIN");
        member.setUser(user);
        memberRepository.save(member);
    }*/
}
