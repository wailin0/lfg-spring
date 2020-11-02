package com.lfg.spring.controller;

import com.lfg.spring.model.Like;
import com.lfg.spring.model.DTO.LikeDto;
import com.lfg.spring.service.LikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LikeController {

    @Autowired
    private LikeService likeService;


    // get like, dislike and comment count as a json
    @GetMapping("/post/{postId}/vote")
    public ResponseEntity<Map<String, Integer>> getLikeAndCommentCount(@PathVariable Long postId) {
        
        return likeService.getLikesFromAPost(postId);
    }


    @GetMapping("/post/{postId}/user/{vote}")
    public Like getUserLikeByPostIdAndUserId(@PathVariable String vote,
                                              @PathVariable Long postId) throws Exception {

        return likeService.getLoggedInUserVotes(postId, vote);
    }

    @PostMapping("/post/{postId}/user/vote")
    public ResponseEntity<Like> like(@PathVariable Long postId,
                                     @RequestBody LikeDto likeDto) {
        
        likeService.like(likeDto, postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post/{postId}/user/vote")
    public void changeVote(@PathVariable Long postId,
                           @RequestBody Like like) {

        likeService.changeVotes(postId, like);
    }


    @DeleteMapping("/post/{postId}/user/{vote}")
    public ResponseEntity<Like> delete(@PathVariable String vote,
                                       @PathVariable Long postId) {
        
        likeService.delete(postId, vote);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
