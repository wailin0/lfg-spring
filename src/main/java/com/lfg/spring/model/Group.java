package com.lfg.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="groups")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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