package com.lfg.spring.service;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Group;
import com.lfg.spring.model.Post;
import com.lfg.spring.model.User;
import com.lfg.spring.repository.GroupRepository;
import com.lfg.spring.repository.PostRepository;
import com.lfg.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private JWTUtil jwtUtil;

   /* public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public List<Post> getAllPostFromAGroup(Long groupId) {
        return postRepository.findByGroup_Id(groupId);
    }

    public void savePost(String username, Long groupId, Post post) {
        User user = userRepository.findByUsername(username);
        Group group = groupRepository.findById(groupId).orElse(null);
        post.setUser(user);
        post.setGroup(group);
        postRepository.save(post);
    }

    public void deletePost(Long postId, String username) throws Exception {
        User user = userRepository.findByUsername(username);
        Post post = postRepository.findById(postId).orElse(null);
        if(user.getUserId().equals(post.getUser().getUserId())){
            postRepository.deleteById(postId);
        }
        else{
            throw new Exception("Error: that post is not yours to delete");
        }*/
    

    public Post getReference(Long postId){

        return postRepository.getOne(postId);
    }
}

