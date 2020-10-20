package com.lfg.spring.service;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Group;
import com.lfg.spring.model.Post;
import com.lfg.spring.model.User;
import com.lfg.spring.model.DTO.PostDto;
import com.lfg.spring.repository.GroupRepository;
import com.lfg.spring.repository.PostRepository;
import com.lfg.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    public List<Post> getAll(){

        return postRepository.findAll();
    }

    public List<Post> getByGroupId(Long groupId) {
        
        return postRepository.findAllByGroupId(groupId);
    }

    public List<Post> getByUserId(Long userId) {
        
        return postRepository.findAllByUserId(userId);
    }

    public Post create(PostDto postDto){
        
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setUser(userService.getReference(postDto.getUserId()));
        post.setGroup(groupService.getReference(postDto.getGroupId()));
        post.setCreatedAt(new Date());

        return postRepository.save(post);
    }

    public void delete(Long postId){

        User user = authService.getCurrentLoggedInUser();

        if(!postRepository.isUserOwensPost(1L, postId))
            //TODO: throw custom message (post-specific)
            return;

        postRepository.deleteById(postId);
    }

    public Post getReference(Long postId){

        return postRepository.getOne(postId);
    }
}

