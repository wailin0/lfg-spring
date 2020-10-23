package com.lfg.spring.repository;

import com.lfg.spring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN FETCH p.group WHERE p.group.id = :groupId")
    public List<Post> findAllByGroupId(Long groupId);
    
    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.user.id = :userId")
    public List<Post> findAllByUserId(Long userId);

    @Query("SELECT count(p)>0 FROM Post p WHERE p.user.id = :userId AND p.id = :postId")
    public Boolean isUserOwensPost(Long userId, Long postId);
    
}
