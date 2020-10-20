package com.lfg.spring.service;

import java.util.Date;
import java.util.List;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Group;
import com.lfg.spring.model.Member;
import com.lfg.spring.model.User;
import com.lfg.spring.model.DTO.MemberDto;
import com.lfg.spring.model.enums.MemberRole;
import com.lfg.spring.repository.GroupRepository;
import com.lfg.spring.repository.MemberRepository;
import com.lfg.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;


    public List<Member> getByGroupId(Long groupId) {

        return memberRepository.findAllByGroupId(groupId);
    }

    public List<Member> getAll(){

        return memberRepository.findAll();
    }

    // create membership and join a group
    public Member create(Long groupId){
        Member member = new Member();
        User user = userService.getCurrentLoggedInUser();

        if(memberRepository.isUserInGroup(user.getUserId(), groupId));
            // throw an error
            
        member.setGroup(groupService.getReference(groupId));
        member.setJoinedAt(new Date());
        member.setRole(MemberRole.MEMBER_ROLE.name());
        member.setDisabled(false);
        member.setUser(user);

        return memberRepository.save(member);
    }

    public Member createAdminIn(Group group){
        User user = userService.getCurrentLoggedInUser();

        Member member = new Member();
        member.setGroup(group);
        member.setJoinedAt(new Date());
        member.setRole(MemberRole.ADMIN_ROLE.name());
        member.setDisabled(false);
        member.setUser(user);

        return member;
    }
    
    
    public void delete(Long groupId) {
        User user = userService.getCurrentLoggedInUser();

        if(!memberRepository.isUserInGroup(user.getUserId(), groupId));
            // throw an error
        
        memberRepository.deleteByGroupAndUserId(user.getUserId(), groupId);
    }
    
}
