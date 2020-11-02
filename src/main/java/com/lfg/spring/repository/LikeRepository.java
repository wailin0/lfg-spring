package com.lfg.spring.repository;

import com.lfg.spring.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

@Transactional
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT l FROM Like l WHERE l.post.id = :postId")
    public List<Like> findByPostId(Long postId);

    @Query("SELECT l FROM Like l WHERE l.user.id = :userId")
    public List<Like> findByUserId(Long userId);

    @Query("SELECT count(l)>0 FROM Like l WHERE l.user.id = :userId AND l.id = :postId")
    public Boolean isPostLikedByUser(Long postId, Long userId);

    @Modifying
    @Query("UPDATE Like l set l.liked = :liked WHERE l.user.id = :userId AND l.id = :postId")
    public void updateByPostAndUserId(Long postId, Long userId, boolean liked);

    @Modifying
    @Query("DELETE FROM Like l WHERE l.user.id = :userId AND l.id = :postId")
    public void deleteByUserAndPostId(Long postId, Long userId);

    int countByPostPostIdAndLiked(Long postId, boolean b);

    Like findByPostPostIdAndUserUserIdAndLiked(Long postId, Long userId, boolean b);

    void deleteByPostPostIdAndUserUserIdAndLiked(Long postId, Long userId, boolean b);

    Like findByPost_PostIdAndUser_UserId(Long postId, Long userId);
}
