package com.lfg.spring.controller;

import com.lfg.spring.model.Users;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class RegisterController {

    @Autowired
    private UsersRepository usersRepository;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    @GetMapping("/user/{id}")
    public Optional<Users> getUserById(@PathVariable("id") Long userId) {
        return usersRepository.findById(userId);
    }


    @GetMapping("/register")
    public List<Users> re() {
        return usersRepository.findAll();
    }

    @PostMapping("/register")
    public void register(@RequestBody Users user){
//        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole("USER");
        usersRepository.save(user);
    }
}
