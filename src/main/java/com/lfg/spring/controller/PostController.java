package com.lfg.spring.controller;

import com.lfg.spring.model.Post;
import com.lfg.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/post")
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    @PostMapping("/post")
    public void savePost(@RequestBody Post post){
        postRepository.save(post);
    }


}
