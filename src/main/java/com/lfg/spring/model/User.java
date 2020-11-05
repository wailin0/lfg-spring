package com.lfg.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private Date createdAt;

    private String roles;
    private boolean accountNonExpired;
    private boolean accountNotLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private boolean isOnline;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.stream(roles.split(","))
            .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {

        return accountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {

        return enabled;
    }

}