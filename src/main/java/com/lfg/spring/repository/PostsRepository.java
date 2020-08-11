package com.lfg.spring.repository;

import com.lfg.spring.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    void deleteByIdAndGroupId(Long postId, Long groupId);

    List<Posts> findByGroupId(Long groupId);
}
