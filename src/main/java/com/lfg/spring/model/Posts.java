package com.lfg.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;

    @ManyToOne()
    @JoinColumn(name = "username")
    private Users users;

    @ManyToOne()
    @JoinColumn(name = "groups_id")
    private Groups groups;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;


}
