package com.lfg.spring.service;

import java.util.Optional;

import com.lfg.spring.model.User;
import com.lfg.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private BCryptPasswordEncoder encoder;

    public void registerUser(User user) {
        user.setRole("USER");
        user.setEnabled(true); // for testing
        userRepository.save(user);
    }

    public User getReference(Long userId) {

        return userRepository.getOne(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isPresent())
            return userOptional.get();

        log.error("User not found with username {}", username);
        throw new UsernameNotFoundException(username);
    }
    
}
