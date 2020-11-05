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
@Table(name="community")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String topic;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String type;



    @ManyToOne
    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Post> post;

    @JsonIgnore
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Member> member;

    private boolean disabled;

    private Date createdAt;

}