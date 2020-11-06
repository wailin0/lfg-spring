package com.lfg.spring.repository;

import com.lfg.spring.model.Friendship;
import com.lfg.spring.model.projections.UserId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    // gets usernames of a user's friends
    @Query("SELECT f.friendOf.userId FROM Friendship f WHERE f.user.id = :userId AND f.friendOf.online = TRUE")
    public List<UserId> findOnlineFriendsOf(Long userId);

}