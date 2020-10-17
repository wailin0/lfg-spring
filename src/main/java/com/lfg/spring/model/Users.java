package com.lfg.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @NotNull
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private boolean isEnabled;
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Posts> post;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Members> members;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;

}
