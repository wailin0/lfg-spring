package com.lfg.spring.controller;

import com.lfg.spring.model.User;
import com.lfg.spring.repository.UserRepository;
import com.lfg.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/all")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;



    @GetMapping("/register")
    public List<User> re() {
        return userRepository.findAll();
    }


    /*// register user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        System.out.println(user.getUsername());
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken");
        }

        else if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Account with that email already exist");
        }
        userService.registerUser(user);
        return ResponseEntity.ok("User Registration successful");
    }*/


    // for async checking username and email while registering

    /*@GetMapping("/validation/username/{username}")
    @ResponseBody
    public boolean checkExistingUsername(@PathVariable String username) {
        if (userRepository.existsByUsername(username)) {
            return true;
        }
        else {
            return false;
        }
    }*/

    /*@GetMapping("/validation/email/{email}")
    @ResponseBody
    public boolean checkExistingEmail(@PathVariable String email) {
        if (userRepository.existsByEmail(email)) {
            return true;
        }
        else {
            return false;
        }
    }*/


}
