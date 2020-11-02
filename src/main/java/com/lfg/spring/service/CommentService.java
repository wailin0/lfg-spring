package com.lfg.spring.service;

import java.util.Date;
import java.util.List;

import com.lfg.spring.model.Comment;
import com.lfg.spring.model.DTO.CommentDto;
import com.lfg.spring.model.Post;
import com.lfg.spring.model.User;
import com.lfg.spring.repository.CommentRepository;

import com.lfg.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private AuthService authService;

    public Comment create(CommentDto commentDto, Long postId){
        User user = authService.getCurrentLoggedInUser();
        Post post = postRepository.findById(postId).orElse(null);
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setCreatedAt(new Date());
        comment.setUser(user);
        comment.setPost(post);
        
        return commentRepository.save(comment);
    }


    public List<Comment> getByPostId(Long postId){
        
        return commentRepository.findAllByPostId(postId);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(Comment comment, Long commentId) {
        Comment commentToUpdate = commentRepository.findById(commentId).orElse(null);
        commentToUpdate.setBody(comment.getBody());
        commentRepository.save(commentToUpdate);

        return commentToUpdate;
    }
}
