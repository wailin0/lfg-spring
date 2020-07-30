package com.lfg.spring.repository;

import com.lfg.spring.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Set<Likes> findByPostId(Long postId);

    void deleteByPostIdAndUserId(Long postId, Long userId);

    Likes findByPostIdAndUserId(Long postId, Long userId);
}
