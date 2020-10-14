package com.lfg.spring.repository;

import com.lfg.spring.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Set<Comments> findByPosts_Id(Long postId);
}
