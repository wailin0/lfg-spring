package com.lfg.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @MapsId
    @OneToOne
    @JoinColumn(name="userId")
    private User toUser;

    @MapsId
    @OneToOne
    @JoinColumn(name="userId")
    private User fromUser;

    private Date sentAt;

    private String content;
    
}