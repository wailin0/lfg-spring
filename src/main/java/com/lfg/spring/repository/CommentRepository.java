package com.lfg.spring.repository;

import com.lfg.spring.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
