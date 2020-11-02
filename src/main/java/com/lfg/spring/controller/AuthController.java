package com.lfg.spring.controller;

import com.lfg.spring.model.User;
import com.lfg.spring.model.DTO.LoginDto;
import com.lfg.spring.model.DTO.SignupDto;
import com.lfg.spring.repository.UserRepository;
import com.lfg.spring.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/all")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<User> signup(@RequestBody SignupDto signupDto){

        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);    
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){ 
        
        return new ResponseEntity<>(authService.authenticate(loginDto.getUsername(), loginDto.getPassword()), HttpStatus.OK);
    }

    //checking username and email while registering
    @GetMapping("/validation/username/{username}")
    @ResponseBody
    public boolean checkExistingUsername(@PathVariable String username) {
        if (userRepository.existsByUsername(username)) {
            return true;
        }
        else {
            return false;
        }
    }

    @GetMapping("/validation/email/{email}")
    @ResponseBody
    public boolean checkExistingEmail(@PathVariable String email) {
        if (userRepository.existsByEmail(email)) {
            return true;
        }
        else {
            return false;
        }
    }
    
}