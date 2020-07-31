package com.lfg.spring.repository;

import com.lfg.spring.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface LikesRepository extends JpaRepository<Likes, Long> {


    Set<Likes> findByPostIdAndLiked(Long postId, boolean liked);

    void deleteByPostIdAndUserIdAndLiked(Long postId, Long userId, boolean liked);

    Likes findByPostIdAndUserIdAndLiked(Long postId, Long userId, boolean liked);

    Likes findByPostIdAndUserId(Long postId, Long userId);
}
