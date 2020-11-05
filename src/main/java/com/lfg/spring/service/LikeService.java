package com.lfg.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfg.spring.model.Like;
import com.lfg.spring.model.Post;
import com.lfg.spring.model.User;
import com.lfg.spring.model.DTO.LikeDto;
import com.lfg.spring.repository.CommentRepository;
import com.lfg.spring.repository.LikeRepository;

import com.lfg.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;

    public ResponseEntity<Map<String, Integer>> getLikesFromAPost(Long postId) {

        int likeCount = likeRepository.countByPostPostIdAndLiked(postId, true);
        int dislikeCount = likeRepository.countByPostPostIdAndLiked(postId, false);
        int commentCount = commentRepository.countByPostPostId(postId);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("dislikeCount", dislikeCount);
        map.put("commentCount", commentCount);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    public void changeVotes(Long postId, Like like) {
        User user = authService.getCurrentLoggedInUser();
        Like voteToChange = likeRepository.findByPost_PostIdAndUser_UserId(postId, user.getUserId());
        voteToChange.setLiked(like.isLiked());
        likeRepository.save(voteToChange);
    }


    public List<Like> getByUserId(Long userId) {

        return likeRepository.findByUserId(userId);
    }

    public void like(LikeDto likeDto, Long postId) {
        User user = authService.getCurrentLoggedInUser();
        create(likeDto, user, postId);
    }

    public void delete(Long postId, String vote) {
        User user = authService.getCurrentLoggedInUser();
        if (vote.equals("like")) {
            likeRepository.deleteByPostPostIdAndUserUserIdAndLiked(postId, user.getUserId(), true);
        } else if (vote.equals("dislike")) {
            likeRepository.deleteByPostPostIdAndUserUserIdAndLiked(postId, user.getUserId(), false);
        }
    }

    private void create(LikeDto likeDto, User user, Long postId) {

        Like like = new Like();
        Post post = postRepository.findById(postId).orElse(null);
        like.setLiked(likeDto.isLiked());
        like.setPost(post);
        like.setUser(user);

        likeRepository.save(like);
    }

    private void update(Long postId, Long userId, boolean liked) {

        likeRepository.updateByPostAndUserId(postId, userId, liked);
    }

    public Like getLoggedInUserVotes(Long postId, String vote) throws Exception {
        User user = authService.getCurrentLoggedInUser();
        if (vote.equals("like")) {
            return likeRepository.findByPostPostIdAndUserUserIdAndLiked(postId, user.getUserId(), true);
        } else if (vote.equals("dislike")) {
            return likeRepository.findByPostPostIdAndUserUserIdAndLiked(postId, user.getUserId(), false);
        } else {
            throw new Exception("wrong vote ");
        }
    }
}
