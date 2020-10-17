package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Post;
import com.lfg.spring.model.DTO.PostDto;
import com.lfg.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> getAllPost(){

        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK); 
    }

    @GetMapping("/api/posts/{groupId}")
    public ResponseEntity<List<Post>> getPostsByGroupId(@PathVariable Long groupId) {

        return new ResponseEntity<>(postService.getByGroupId(groupId), HttpStatus.OK); 
    }

    @PostMapping("/api/auth/posts/{groupId}")
    public ResponseEntity<Post> savePostController(@RequestBody PostDto postDto){

        return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/auth/posts/{postId}")
    public ResponseEntity<?> deletePostController(@PathVariable Long postId) {

        postService.delete(postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
