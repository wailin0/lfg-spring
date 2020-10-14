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
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String topic;
    private String description;
    private String type;

    @OneToMany
    private List<Posts> posts;

    @OneToMany
    private List<Members> members;

    private boolean disabled;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;

}