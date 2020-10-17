package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Groups;
import com.lfg.spring.repository.GroupsRepository;
import com.lfg.spring.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class GroupsController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private JWTUtil jwtUtil;

    //for testing
    @GetMapping("/group")
    public List<Groups> getAllGroup() {
        return groupsRepository.findAll();
    }

    @GetMapping("/group/{topic}")
    public List<Groups> getGroupByTopic(@PathVariable String topic) {
        return groupsRepository.findByTopic(topic);
    }


    @PostMapping("/group")
    public void createGroup(@RequestBody Groups group, HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        groupService.createGroup(username, group);
    }

}
