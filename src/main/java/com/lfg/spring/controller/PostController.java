package com.lfg.spring.controller;

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

    @GetMapping("/all/post")
    public ResponseEntity<List<Post>> getAll(){

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
