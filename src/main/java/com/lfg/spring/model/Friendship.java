package com.lfg.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.lfg.spring.model.enums.FriendshipStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendshipId;

    @MapsId
    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @MapsId
    @OneToOne
    @JoinColumn(name="userId")
    private User friendOf;

    private FriendshipStatus status;

}