package com.lfg.spring.controller;

import com.cloudinary.utils.ObjectUtils;
import com.lfg.spring.model.Post;
import com.lfg.spring.model.DTO.PostDto;
import com.lfg.spring.service.PostService;
import com.lfg.spring.component.CloudinaryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/all/post")
    public ResponseEntity<List<Post>> getAll() {

        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/group/{groupId}/post")
    public ResponseEntity<List<Post>> getByGroupId(@PathVariable Long groupId) {

        return new ResponseEntity<>(postService.getByGroupId(groupId), HttpStatus.OK);
    }

    @PostMapping("/group/{groupId}/post")
    public ResponseEntity<Post> create(@PathVariable Long groupId, @RequestBody PostDto postDto) throws Exception {

        return new ResponseEntity<>(postService.create(postDto, groupId), HttpStatus.CREATED);
    }

    //uploading media post
    @PostMapping("/group/{groupId}/media")
    public ResponseEntity<Post> createMediaPost(@RequestParam("file") MultipartFile file,
                                                @RequestParam("title") String title,
                                                @PathVariable Long groupId) throws Exception {

        Post post  = postService.createMediaPost(file, title, groupId);

        return ResponseEntity.status(201).body(post);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> delete(@PathVariable Long postId) throws Exception {

        postService.delete(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<Post> updatePostController(@PathVariable Long postId,
                                                     @RequestBody Post post) throws Exception {
        Post updatedPost = postService.updatePost(post, postId);
        return ResponseEntity.ok(updatedPost);
    }
}
