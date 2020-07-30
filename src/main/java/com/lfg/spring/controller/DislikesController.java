package com.lfg.spring.controller;

import com.lfg.spring.model.Dislikes;
import com.lfg.spring.repository.DislikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
public class DislikesController {

    @Autowired
    private DislikesRepository dislikesRepository;

    @GetMapping("/post/{postId}/dislike")
    public Set<Dislikes> getDislikesByPostId (@PathVariable Long postId) {
        return dislikesRepository.findByPostId(postId);
    }

    @PostMapping("/dislike")
    public void saveUserDislikeByPostId (@RequestBody Dislikes dislike) {
        dislikesRepository.save(dislike);
    }

    @GetMapping("/dislike/post/{postId}/user/{userId}")
    public Dislikes getUserDislikeByPostIdAndUserId(@PathVariable Long postId, @PathVariable Long userId){
        return dislikesRepository.findByPostIdAndUserId(postId, userId);
    }

    @DeleteMapping("/dislike/post/{postId}/user/{userId}")
    public void deleteUserDislikeByPostIdAndUserId(@PathVariable Long postId,@PathVariable Long userId){
        // to do
        // if requested user id = login user id
        dislikesRepository.deleteByPostIdAndUserId(postId, userId);
    }
}
