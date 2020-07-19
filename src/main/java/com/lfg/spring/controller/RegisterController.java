package com.lfg.spring.controller;

import com.lfg.spring.model.Users;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RegisterController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/user/{id}")
    public Optional<Users> getUserById(@PathVariable("id") Long userId) {
        return usersRepository.findById(userId);
    }

    @PostMapping("/register")
    public void register(@RequestBody Users users){
        usersRepository.save(users);
    }
}
