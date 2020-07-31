package com.lfg.spring.repository;

import com.lfg.spring.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Set<Comments> findByPostId(Long postId);
}
