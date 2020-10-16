package com.lfg.spring.controller;

import com.lfg.spring.JWT.JWTUtil;
import com.lfg.spring.model.Post;
import com.lfg.spring.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class PostController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/api/all/post")
    public List<Post> getAllPost(){
        return null;
    }

    @GetMapping("/api/all/post/{groupId}")
    public List<Post> getPostsByGroupId(@PathVariable Long groupId) {
        return null;
    }

    @PostMapping("/api/auth/post/{groupId}")
    public void savePostController(@PathVariable Long groupId, @RequestBody Post post, HttpServletRequest request){
        /*String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        postsService.savePost(username, groupId, post);*/
    }

    @DeleteMapping("/api/auth/post/{postId}")
    public void deletePostController(@PathVariable Long postId, HttpServletRequest request) throws Exception {
        /*String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        postsService.deletePost(postId, username);*/
    }
}
