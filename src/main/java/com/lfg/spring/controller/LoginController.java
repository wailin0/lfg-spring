package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTRequest;
import com.lfg.spring.JWT.JWTResponse;
import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Users;
import com.lfg.spring.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest jwtRequest)
            throws Exception {
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }
    @GetMapping("/login")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);
        Users user = (Users) userDetailsServiceImpl.loadUserByUsername(username);
        if (jwtUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtUtil.refreshToken(token);
            return ResponseEntity.ok(new JWTResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}