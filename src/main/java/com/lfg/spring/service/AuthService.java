package com.lfg.spring.service;

import java.util.Date;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.User;
import com.lfg.spring.model.DTO.SignupDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;
    

    public User getCurrentLoggedInUser(){ 

        return userService.loadUserByUsername(getUsernameFromSecurityContextHolder()); 
    }

    private String getUsernameFromSecurityContextHolder() { 
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((User)principal).getUsername();
        else
            return principal.toString();
    }

    public User signup(SignupDto signupDto) {

        User user = new User();
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setEnabled(true);
        user.setRole("USER");
        user.setCreatedAt(new Date());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
    
        userService.save(user);

        return user;
    }

    public String authenticate(String username, String password) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        
        return jwtUtil.generateToken((User) authenticate.getPrincipal());
    }

}
