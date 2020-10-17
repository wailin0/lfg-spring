package com.lfg.spring.service;

import java.util.Date;
import java.util.List;

import com.lfg.spring.model.Comment;
import com.lfg.spring.model.DTO.CommentDto;
import com.lfg.spring.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    public Comment create(CommentDto commentDto){

        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setCreatedAt(new Date());
        comment.setUser(userService.getReference(commentDto.getUserId()));
        comment.setPost(postService.getReference(commentDto.getPostId()));
        
        return commentRepository.save(comment);
    }

    public List<Comment> getByPostId(Long postId){
        
        return commentRepository.findAllByPostId(postId);
    }

    public List<Comment> getByUserId(Long userId){

        return commentRepository.findAllByUserId(userId);
    }
}
