package com.lfg.spring.controller;

import com.lfg.spring.model.Comments;
import com.lfg.spring.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class CommentsController {

    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping("/comment/post/{postId}")
    public Set<Comments> getCommentsByPostId(@PathVariable Long postId) {
        return commentsRepository.findByPostId(postId);
    }

    @PostMapping("/comment")
    public void saveComment(@RequestBody Comments comments){
        commentsRepository.save(comments);
    }




}
