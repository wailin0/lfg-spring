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

    @GetMapping("/post/{postId}/like")
    public Set<Likes> getLikesByPostId (@PathVariable Long postId) {
        return likesRepository.findByPostId(postId);
    }

    @PostMapping("/like")
    public void saveUserLikeByPostId (@RequestBody Likes like) {
        likesRepository.save(like);
    }

    @GetMapping("/like/post/{postId}/user/{userId}")
    public Likes getUserLikeByPostIdAndUserId(@PathVariable Long postId, @PathVariable Long userId){
        return likesRepository.findByPostIdAndUserId(postId, userId);
    }

    @DeleteMapping("/like/post/{postId}/user/{userId}")
    public void deleteUserLikeByPostIdAndUserId(@PathVariable Long postId,@PathVariable Long userId){
        // to do
        // if requested user id = login user id
        likesRepository.deleteByPostIdAndUserId(postId, userId);
    }

}
