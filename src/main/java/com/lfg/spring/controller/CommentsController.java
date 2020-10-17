package com.lfg.spring.controller;

import com.lfg.spring.model.Comments;
import com.lfg.spring.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/group")
public class CommentsController {

    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping("/{groupId}/post/{postId}/comment")
    public Set<Comments> getCommentsByPostId(@PathVariable Long postId) {
        return commentsRepository.findByPosts_Id(postId);
    }

    @PostMapping("/{groupId}/post/{postId}/comment")
    public void saveAComment(@RequestBody Comments comments){
        commentsRepository.save(comments);
    }

    @DeleteMapping("/{groupId}/post/{postId}/comment/{commentId}")
    public void deleteAComment() {
        // to do
    }

    @PutMapping("/{groupId}/post/{postId}/comment/{commentId}")
    public void updateAComment() {
        // to do
    }


}
