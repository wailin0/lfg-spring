package com.lfg.spring.controller;

import com.lfg.spring.model.Users;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/all")
public class RegisterController {

    @Autowired
    private UsersRepository usersRepository;

//    @Autowired
//    private BCryptPasswordEncoder encoder;


    @GetMapping("/register")
    public List<Users> re() {
        return usersRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user){
        if (usersRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken");
        }

        else if (usersRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Account with email already exist");
        }
//      user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole("USER");
        usersRepository.save(user);

        return ResponseEntity.ok("User Registration successful");
    }

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
