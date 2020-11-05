package com.lfg.spring.service;

import com.cloudinary.utils.ObjectUtils;
import com.lfg.spring.component.CloudinaryConfig;
import com.lfg.spring.model.Group;
import com.lfg.spring.model.Post;
import com.lfg.spring.model.User;
import com.lfg.spring.model.DTO.PostDto;
import com.lfg.spring.repository.GroupRepository;
import com.lfg.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private CloudinaryConfig cloudinaryConfig;

    @Autowired
    private GroupRepository groupRepository;

    public List<Post> getAll() {

        return postRepository.findAll();
    }

    public List<Post> getByGroupId(Long groupId) {

        return postRepository.findAllByGroupId(groupId);
    }

    public List<Post> getByUserId(Long userId) {

        return postRepository.findAllByUserId(userId);
    }

    public Post create(PostDto postDto, Long groupId) throws Exception {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            throw new Exception("that group doesnt exist");
        }
        User user = authService.getCurrentLoggedInUser();
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setUser(user);
        post.setGroup(group);
        post.setCreatedAt(new Date());

        return postRepository.save(post);
    }

    public void delete(Long postId) throws Exception {

        User user = authService.getCurrentLoggedInUser();
        Post post = getReference(postId);

        if (post != null) {
            if (post.getUser().getUsername().equals(user.getUsername())) {
                postRepository.deleteById(postId);
            } else {
                throw new Exception("Error: that post is not yours to delete");
            }
        }
    }

    public Post getReference(Long postId) {
        return postRepository.getOne(postId);
    }

    public Post updatePost(Post post, Long postId) throws Exception {
        User user = authService.getCurrentLoggedInUser();
        Post postToUpdate = getReference(postId);
        if (postToUpdate != null) {
            if (postToUpdate.getUser().getUsername().equals(user.getUsername())) {
                postToUpdate.setTitle(post.getTitle());
                postToUpdate.setBody(post.getBody());
                return postRepository.save(postToUpdate);
            } else {
                throw new Exception("Error: that post is not yours to update");
            }
        }
        return null;
    }

    public Post createMediaPost(MultipartFile file, String title, Long groupId) throws Exception {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            throw new Exception("that group doesnt exist");
        }
        User user = authService.getCurrentLoggedInUser();
        Post post = new Post();
        try {
            Map uploadResult = cloudinaryConfig.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto", "folder", "lfg/media/",
                            "public_id", file.getOriginalFilename() + ""));
            post.setBody(uploadResult.get("url").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setTitle(title);
        post.setUser(user);
        post.setGroup(group);
        post.setCreatedAt(new Date());

        return postRepository.save(post);
    }
}

