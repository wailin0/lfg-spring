package com.lfg.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "groupId")
    private Group group;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> like;


    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comment;

    private Date createdAt;

}
