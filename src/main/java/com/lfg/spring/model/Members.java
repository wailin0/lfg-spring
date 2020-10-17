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
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne()
    private Users users;


    @ManyToOne()
    private Groups groups;

    private String role;

    private boolean disabled;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date joinedDate;


}
