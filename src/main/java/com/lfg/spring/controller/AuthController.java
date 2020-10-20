package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTRequest;
import com.lfg.spring.JWT.JWTResponse;
import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.User;
import com.lfg.spring.model.DTO.SignupDto;
import com.lfg.spring.service.AuthService;
import com.lfg.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/all")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto){

        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);    
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JWTRequest jwtRequest){ 
        
        return new ResponseEntity<>(authService.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword()), HttpStatus.OK);
    }
}