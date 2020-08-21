package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Users;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/user/userInfo")
    public Users getUserByUsername(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = requestTokenHeader.substring(7);
        String username = jwtUtil.getUsernameFromToken(jwtToken);  //get legit username from token
        return usersRepository.findByUsername(username);
    }


}
