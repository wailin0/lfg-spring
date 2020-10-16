package com.lfg.spring.controller;

import com.lfg.spring.model.Like;
import com.lfg.spring.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class LikeController {

    @Autowired
    private LikeRepository likeRepository;

    @GetMapping("/post/{postId}/{vote}")
    public Set<Like> getLikesByPostId (@PathVariable Long postId, @PathVariable String vote) {
        /*if(vote.equals("like")) {
            return likeRepository.findByPostPostIdAndLiked(postId, true);
        }
        else if(vote.equals("dislike")){
            return likeRepository.findByPostPostIdAndLiked(postId, false);
        }*/
        return null;
    }

    @PostMapping("/{vote}")
    public void saveUserLikeByPostId (@PathVariable String vote, @RequestBody Like like) {
        /*if(vote.equals("like")) {
            likeRepository.save(like);
        }
        else if(vote.equals("dislike")){
            likeRepository.save(like);
        }*/
    }

    @PutMapping("/like/post/{postId}/user/{userId}")
    public void changeVote(@PathVariable Long postId,
                           @PathVariable Long userId,
                           @RequestBody Like like){
        /*Like voteToChange = likeRepository.findByPostIdAndUserId(postId, userId);
        voteToChange.setLiked(like.isLiked());
        likeRepository.save(voteToChange);*/
    }

    @GetMapping("/{vote}/post/{postId}/user/{userId}")
    public Like getUserLikeByPostIdAndUserId(@PathVariable String vote, @PathVariable Long postId, @PathVariable Long userId){
        /*if(vote.equals("like")) {
            return likeRepository.findByPostIdAndUserIdAndLiked(postId, userId, true);
        }
        else if(vote.equals("dislike")){
            return likeRepository.findByPostIdAndUserIdAndLiked(postId, userId, false);
        }*/
        return null;
    }


    @DeleteMapping("/{remove}/post/{postId}/user/{userId}")
    public void deleteUserLikeByPostIdAndUserId(@PathVariable String remove,
                                                @PathVariable Long postId,
                                                @PathVariable Long userId){
        // TODO: replace with authorized userID
        /*
        if(remove.equals("like")) {
            likeRepository.deleteByPostIdAndUserIdAndLiked(postId, userId, true);
        }
        else if(remove.equals("dislike")){
            likeRepository.deleteByPostIdAndUserIdAndLiked(postId, userId, false);
        }*/
    }


}
