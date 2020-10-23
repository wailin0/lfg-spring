package com.lfg.spring.controller;

import com.lfg.spring.model.Comment;
import com.lfg.spring.model.DTO.CommentDto;
import com.lfg.spring.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAll(){
        
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/comments/post/{postId}")
    public ResponseEntity<List<Comment>> getByPostId(@PathVariable Long postId) {
        
        return new ResponseEntity<>(commentService.getByPostId(postId), HttpStatus.OK); 
    }

    @GetMapping("/comments/user/{userId}")
    public ResponseEntity<List<Comment>> getByUserId(@PathVariable Long userId) {
        
        return new ResponseEntity<>(commentService.getByUserId(userId), HttpStatus.OK); 
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> create(@RequestBody CommentDto commentDto){
        
        return new ResponseEntity<>(commentService.create(commentDto), HttpStatus.CREATED);
    }

}
