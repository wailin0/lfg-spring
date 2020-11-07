package com.lfg.spring.model;

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
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="groupId", referencedColumnName="groupId")
    private Group group;

    @MapsId
    @OneToOne
    @JoinColumn(name="userId", referencedColumnName="userId")
    private User user;

    private String role;

    private boolean disabled;

    private Date joinedAt;

}
