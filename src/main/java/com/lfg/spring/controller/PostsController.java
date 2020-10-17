package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Posts;
import com.lfg.spring.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class PostsController {

    @Autowired
    private PostsService postsService;


    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/api/post")
    public List<Posts> getAllPost() {
        return postsService.getAllPost();
    }

    @GetMapping("/api/group/{groupId}/post")
    public List<Posts> getPostsByGroupId(@PathVariable Long groupId) {
        return postsService.getAllPostFromAGroup(groupId);
    }

    @PostMapping("/api/group/{groupId}/post")
    public void savePostController(@PathVariable Long groupId, @RequestBody Posts post, HttpServletRequest request) throws Exception {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        postsService.savePost(username, groupId, post);
    }

    @DeleteMapping("/api/post/{postId}")
    public void deletePostController(@PathVariable Long postId, HttpServletRequest request) throws Exception {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);
        postsService.deletePost(postId, username);
    }

    @PutMapping("/api/post/{postId}")
    public void updatePostController(@PathVariable Long postId, HttpServletRequest request) throws Exception {
        /// to do
    }
}
