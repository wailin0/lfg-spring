package com.lfg.spring.service;

import java.util.List;

import com.lfg.spring.model.Like;
import com.lfg.spring.model.User;
import com.lfg.spring.model.DTO.LikeDto;
import com.lfg.spring.repository.LikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public List<Like> getByPostId(Long postId){

        return likeRepository.findByPostId(postId);
    }

    public List<Like> getByUserId(Long userId){

        return likeRepository.findByUserId(userId);
    }

	public void like(LikeDto likeDto) {
        User user = userService.getCurrentLoggedInUser();

        if(likeRepository.isPostLikedByUser(user.getUserId(), likeDto.getPostId()))
            update(likeDto.getPostId(), user.getUserId(), likeDto.isLiked());
        else
            create(likeDto, user);
    }

    public void delete(LikeDto likeDto) {
        User user = userService.getCurrentLoggedInUser();

        likeRepository.deleteByUserAndPostId(user.getUserId(), likeDto.getPostId());
    }

    private void create(LikeDto likeDto, User user){
        Like like = new Like();

        like.setLiked(likeDto.isLiked());
        like.setPost(postService.getReference(likeDto.getPostId()));
        like.setUser(user);

        likeRepository.save(like);
    }

    private void update(Long postId, Long userId, boolean liked){

        likeRepository.updateByPostAndUserId(postId, userId, liked);
    }
    
}
