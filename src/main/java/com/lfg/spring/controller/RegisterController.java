package com.lfg.spring.controller;

import com.lfg.spring.model.Users;
import com.lfg.spring.repository.UsersRepository;
import com.lfg.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;


    //for testing
    @GetMapping("/register")
    public List<Users> re() {
        return usersRepository.findAll();
    }


    // register user
    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody Users user){
        if(user.getUsername() == null){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is required");
        }
        else if(user.getEmail() == null){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is required");
        }
        else if(user.getPassword() == null){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Password is required");
        }

        if (usersRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken");
        }

        else if (usersRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Account with that email already exist");
        }

        userService.registerUser(user);
        return ResponseEntity.ok("User Registration successful");
    }


    // for async checking username and email while registering

    @GetMapping("/validation/username/{username}")
    @ResponseBody
    public boolean checkExistingUsername(@PathVariable String username) {
        if (usersRepository.existsByUsername(username)) {
            return true;
        }
        else {
            return false;
        }
    }

    @GetMapping("/validation/email/{email}")
    @ResponseBody
    public boolean checkExistingEmail(@PathVariable String email) {
        if (usersRepository.existsByEmail(email)) {
            return true;
        }
        else {
            return false;
        }
    }


}
