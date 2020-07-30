package com.lfg.spring.repository;

import com.lfg.spring.model.Dislikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DislikesRepository extends JpaRepository <Dislikes, Long> {
    Dislikes findByPostIdAndUserId(Long postId, Long userId);

    void deleteByPostIdAndUserId(Long postId, Long userId);

    Set<Dislikes> findByPostId(Long postId);
}
