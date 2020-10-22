package com.lfg.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="groups")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(unique = true)
    private String name;

    private String topic;
    private String description;
    private String type;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Member> members;

    private boolean disabled;

    private Date createdAt;

}