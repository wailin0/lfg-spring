package com.lfg.spring.service;

import java.util.Date;
import java.util.List;

import com.lfg.spring.model.Group;
import com.lfg.spring.model.Member;
import com.lfg.spring.model.User;
import com.lfg.spring.model.enums.MemberRole;
import com.lfg.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthService authService;

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
        User user = authService.getCurrentLoggedInUser();

        if(memberRepository.isUserInGroup(user.getUserId(), groupId))
            // TODO: throw an error
            return null;

        member.setGroup(groupService.getReference(groupId));
        member.setJoinedAt(new Date());
        member.setRole(MemberRole.MEMBER_ROLE.name());
        member.setDisabled(false);
        member.setUser(user);

        return memberRepository.save(member);
    }

    public void createAdminIn(Group group){
        User user = authService.getCurrentLoggedInUser();
        Member member = new Member();
        member.setGroup(group);
        member.setJoinedAt(new Date());
        member.setRole(MemberRole.ADMIN_ROLE.name());
        member.setDisabled(false);
        member.setUser(user);

        memberRepository.save(member);
    }
    
    
    public void delete(Long groupId) {
        User user = authService.getCurrentLoggedInUser();

        if(!memberRepository.isUserInGroup(user.getUserId(), groupId));
            // TODO: throw an error
        
        memberRepository.deleteByGroupAndUserId(user.getUserId(), groupId);
    }
    
}
