package com.lfg.spring.service;

import com.lfg.spring.model.Users;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    public void registerUser(Users user) {
        user.setRole("USER");
        user.setEnabled(true);  // for testing
        usersRepository.save(user);
    }
}
