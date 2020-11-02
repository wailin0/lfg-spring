package com.lfg.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String body;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(nullable = false, name="userId")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false, name="postId")
    private Post post;

}