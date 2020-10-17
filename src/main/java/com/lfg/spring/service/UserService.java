package com.lfg.spring.service;

import com.lfg.spring.model.User;
import com.lfg.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    public void registerUser(User user) {
        user.setRole("USER");
        user.setEnabled(true);  // for testing
        userRepository.save(user);
    }

    public User getReference(Long userId){

        return userRepository.getOne(userId);
    }
    
}
