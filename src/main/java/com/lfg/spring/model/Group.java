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
    private List<Post> post;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Member> member;

    private boolean disabled;

    private Date createdDate;

}