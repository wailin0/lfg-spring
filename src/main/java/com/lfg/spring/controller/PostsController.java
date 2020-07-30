package com.lfg.spring.controller;
import com.lfg.spring.model.Posts;
import com.lfg.spring.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PostsController {

    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/post")
    public List<Posts> getAllPost(){
        return postsRepository.findAll();
    }

    @PostMapping("/post")
    public void savePost(@RequestBody Posts post){
        postsRepository.save(post);
    }

    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable Long postId){
        postsRepository.deleteById(postId);
    }

}
