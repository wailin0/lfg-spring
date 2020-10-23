package com.lfg.spring.controller;

import com.lfg.spring.model.Like;
import com.lfg.spring.model.DTO.LikeDto;
import com.lfg.spring.service.LikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/likes/post/{postId}")
    public ResponseEntity<List<Like>> getByPostId(@PathVariable Long postId) {
        
        return new ResponseEntity<>(likeService.getByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("/likes/user/{userId}")
    public ResponseEntity<List<Like>> getByUserId(@PathVariable Long userId) {
        
        return new ResponseEntity<>(likeService.getByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/likes")
    public ResponseEntity<Like> like(@RequestBody LikeDto likeDto) {
        
        likeService.like(likeDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/likes")
    public ResponseEntity<Like> delete(@RequestBody LikeDto likeDto) {
        
        likeService.delete(likeDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
