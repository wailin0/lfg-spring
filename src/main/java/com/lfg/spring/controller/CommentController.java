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

    @GetMapping("/comments/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        
        return new ResponseEntity<>(commentService.getByPostId(postId), HttpStatus.OK); 
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> saveComment(@RequestBody CommentDto commentDto){
        
        return new ResponseEntity<>(commentService.create(commentDto), HttpStatus.CREATED);
    }

}
