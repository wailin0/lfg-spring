package com.lfg.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="likes")
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long likeId;

    private boolean liked;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
    @JoinColumn(name="userId", referencedColumnName="userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
    @JoinColumn(name="postId", referencedColumnName="postId")
    private Post post;

}
