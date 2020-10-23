package com.lfg.spring.repository;

import com.lfg.spring.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.post.id = :postId")
    public List<Comment> findAllByPostId(Long postId);

    @Query("SELECT c FROM Comment c JOIN FETCH c.post WHERE c.user.id = :userId")
    public List<Comment> findAllByUserId(Long userId);

}
