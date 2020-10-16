package com.lfg.spring.service;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Group;
import com.lfg.spring.model.Member;
import com.lfg.spring.model.User;
import com.lfg.spring.repository.GroupRepository;
import com.lfg.spring.repository.MemberRepository;
import com.lfg.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private GroupRepository groupRepository;

    /*public List<Member> getMemberList(Long groupId) {
        return memberRepository.findByGroup_Id(groupId);
    }

    public void joinGroup(String username, Long groupId){
        User user = userRepository.findByUsername(username);
        Group group = groupRepository.findById(groupId).orElse(null);
        Member member = new Member();
        member.setGroup(group);
        member.setUser(user);
        member.setRole("MEMBER");
        memberRepository.save(member);
    }

    public void leaveGroup(String username, Long groupId) {
        User user = userRepository.findByUsername(username);
        memberRepository.deleteByGroup_IdAndUser_Id(groupId, user.getUserId());
    }
*/
    
}
