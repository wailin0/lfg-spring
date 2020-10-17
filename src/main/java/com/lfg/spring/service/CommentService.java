package com.lfg.spring.service;

import java.util.List;

import com.lfg.spring.model.Comment;
import com.lfg.spring.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment create(Comment comment){
        
        return commentRepository.save(comment);
    }

    public List<Comment> findByPostId(Long postId){
        
        return commentRepository.findAllByPostId(postId);
    }
}
