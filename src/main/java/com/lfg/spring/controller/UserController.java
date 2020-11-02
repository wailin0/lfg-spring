package com.lfg.spring.controller;

import com.lfg.spring.model.User;
import com.lfg.spring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthService authService;

   @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(HttpServletRequest request) {
        
        return new ResponseEntity<>(authService.getCurrentLoggedInUser(), HttpStatus.OK);
    }

}
