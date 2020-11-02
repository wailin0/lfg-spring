package com.lfg.spring.controller;

import com.lfg.spring.model.Comment;
import com.lfg.spring.model.DTO.CommentDto;
import com.lfg.spring.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/post/{postId}/comment")
    public ResponseEntity<List<Comment>> getByPostId(@PathVariable Long postId) {
        
        return new ResponseEntity<>(commentService.getByPostId(postId), HttpStatus.OK); 
    }

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<Comment> create(@PathVariable Long postId, @RequestBody CommentDto commentDto){
        
        return new ResponseEntity<>(commentService.create(commentDto, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteAComment(@PathVariable Long commentId) {

        commentService.deleteComment(commentId);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<Comment> updateAComment(@PathVariable Long commentId,
                                                   @RequestBody Comment comment) {

        return ResponseEntity.ok().body(commentService.updateComment(comment, commentId));
    }

}
