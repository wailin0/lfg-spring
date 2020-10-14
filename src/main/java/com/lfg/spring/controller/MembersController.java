package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Members;
import com.lfg.spring.repository.MembersRepository;
import com.lfg.spring.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class MembersController {

    @Autowired
    private MembersService membersService;

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/member")
    public List<Members> getAll(){
        return membersRepository.findAll();
    }


    //to do
    /// get member by userid



    @GetMapping("/group/{groupId}/members")
    public List<Members> getMembersByGroupId(@PathVariable Long groupId){
        return membersService.getMemberList(groupId);
    }

    @PostMapping("/joinGroup/{groupId}")
    public void joinGroupController (@PathVariable Long groupId, HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        membersService.joinGroup(username, groupId);
    }

    @DeleteMapping("/leaveGroup/{groupId}")
    public void leaveGroupController (@PathVariable Long groupId, HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        membersService.leaveGroup(username, groupId);
    }




}
