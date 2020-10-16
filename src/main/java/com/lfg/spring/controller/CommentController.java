package com.lfg.spring.controller;

import com.lfg.spring.model.Comment;
import com.lfg.spring.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comment/{postId}")
    public Set<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return null;
    }

    @PostMapping("/comment/{postId}")
    public void saveComment(@RequestBody Comment comment){
        
    }




}
