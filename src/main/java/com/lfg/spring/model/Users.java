package com.lfg.spring.model;

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

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private boolean isEnabled;
    private String role;


    @OneToMany(mappedBy = "users")
    private List<Posts> post;

    @OneToOne
    private Members members;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;

}
