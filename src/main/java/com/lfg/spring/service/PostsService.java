package com.lfg.spring.service;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Groups;
import com.lfg.spring.model.Posts;
import com.lfg.spring.model.Users;
import com.lfg.spring.repository.GroupsRepository;
import com.lfg.spring.repository.PostsRepository;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {


    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private JWTUtil jwtUtil;

    public List<Posts> getAllPost(){
        return postsRepository.findAll();
    }

    public List<Posts> getAllPostFromAGroup(Long groupId) {
        return postsRepository.findByGroups_Id(groupId);
    }

    public void savePost(String username, Long groupId, Posts post) {
        Users user = usersRepository.findByUsername(username);
        Groups group = groupsRepository.findById(groupId).orElse(null);
        post.setUsers(user);
        post.setGroups(group);
        postsRepository.save(post);
    }

    public void deletePost(Long postId, String username) throws Exception {
        Users user = usersRepository.findByUsername(username);
        Posts post = postsRepository.findById(postId).orElse(null);
        if(user.getId().equals(post.getUsers().getId())){
            postsRepository.deleteById(postId);
        }
        else{
            throw new Exception("Error: that post is not yours to delete");
        }
    }









}
