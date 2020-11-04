package com.lfg.spring.service;

import java.util.List;

import com.lfg.spring.model.Friendship;
import com.lfg.spring.model.enums.FriendshipStatus;
import com.lfg.spring.model.projections.UserId;
import com.lfg.spring.repository.FriendshipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserService userService;

    public List<UserId> getOnlineFriendsOf(Long userId){

        return friendshipRepository.findOnlineFriendsOf(userId);
    }

    public Friendship beFriendWith(Long user, Long friendOf) {

        Friendship friendship = new Friendship();
        friendship.setUser(userService.getReference(user));
        friendship.setFriendOf(userService.getReference(friendOf));
        friendship.setStatus(FriendshipStatus.ACCEPTED);

        return friendshipRepository.save(friendship);
    }

    public void removeFriendshipOf(Long user, Long friendOf){ }
}