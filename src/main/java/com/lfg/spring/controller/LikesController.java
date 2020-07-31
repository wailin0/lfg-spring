package com.lfg.spring.controller;

import com.lfg.spring.model.Likes;
import com.lfg.spring.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
public class LikesController {

    @Autowired
    private LikesRepository likesRepository;

    @GetMapping("/post/{postId}/{vote}")
    public Set<Likes> getLikesByPostId (@PathVariable Long postId, @PathVariable String vote) {
        if(vote.equals("like")) {
            return likesRepository.findByPostIdAndLiked(postId, true);
        }
        else if(vote.equals("dislike")){
            return likesRepository.findByPostIdAndLiked(postId, false);
        }
        return null;
    }

    @PostMapping("/{vote}")
    public void saveUserLikeByPostId (@PathVariable String vote, @RequestBody Likes like) {
        if(vote.equals("like")) {
            likesRepository.save(like);
        }
        else if(vote.equals("dislike")){
            likesRepository.save(like);
        }
    }

    @PutMapping("/like/post/{postId}/user/{userId}")
    public void changeVote(@PathVariable Long postId,
                           @PathVariable Long userId,
                           @RequestBody Likes like){
        Likes voteToChange = likesRepository.findByPostIdAndUserId(postId, userId);
        voteToChange.setLiked(like.isLiked());
        likesRepository.save(voteToChange);
    }

    @GetMapping("/{vote}/post/{postId}/user/{userId}")
    public Likes getUserLikeByPostIdAndUserId(@PathVariable String vote, @PathVariable Long postId, @PathVariable Long userId){
        if(vote.equals("like")) {
            return likesRepository.findByPostIdAndUserIdAndLiked(postId, userId, true);
        }
        else if(vote.equals("dislike")){
            return likesRepository.findByPostIdAndUserIdAndLiked(postId, userId, false);
        }
        return null;
    }


    @DeleteMapping("/{remove}/post/{postId}/user/{userId}")
    public void deleteUserLikeByPostIdAndUserId(@PathVariable String remove,
                                                @PathVariable Long postId,
                                                @PathVariable Long userId){
        // to do
        // if requested user id = login user id
        if(remove.equals("like")) {
            likesRepository.deleteByPostIdAndUserIdAndLiked(postId, userId, true);
        }
        else if(remove.equals("dislike")){
            likesRepository.deleteByPostIdAndUserIdAndLiked(postId, userId, false);
        }
    }


}
