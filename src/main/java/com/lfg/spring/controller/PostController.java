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
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAll(){

        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK); 
    }

    @GetMapping("/posts/{groupId}")
    public ResponseEntity<List<Post>> getByGroupId(@PathVariable Long groupId) {

        return new ResponseEntity<>(postService.getByGroupId(groupId), HttpStatus.OK); 
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody PostDto postDto){

        return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> delete(@PathVariable Long postId) {

        postService.delete(postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
