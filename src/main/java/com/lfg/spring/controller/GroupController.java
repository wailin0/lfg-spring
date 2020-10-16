package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Group;
import com.lfg.spring.repository.GroupRepository;
import com.lfg.spring.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/group")
    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }

    @GetMapping("/group/{topic}")
    public List<Group> getGroupByTopic(@PathVariable String topic) {
        return null;
    }


    @PostMapping("/group/createGroup")
    public void createGroup(@RequestBody Group group, HttpServletRequest request) {

    }

}
