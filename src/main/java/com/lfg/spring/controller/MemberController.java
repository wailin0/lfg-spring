package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Member;
import com.lfg.spring.repository.MemberRepository;
import com.lfg.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository membersRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/member")
    public List<Member> getAll(){
        return membersRepository.findAll();
    }

    @GetMapping("/group/{groupId}/members")
    public List<Member> getMembersByGroupId(@PathVariable Long groupId){
        return null;
    }

    @PostMapping("/joinGroup/{groupId}")
    public void joinGroupController (@PathVariable Long groupId, HttpServletRequest request) {
        /*String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        memberService.joinGroup(username, groupId);*/
    }

    @DeleteMapping("/leaveGroup/{groupId}")
    public void leaveGroupController (@PathVariable Long groupId, HttpServletRequest request) {
        /*String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        memberService.leaveGroup(username, groupId);*/
    }

    // TODO: get member by userid
    
}
