package com.lfg.spring.repository;

import com.lfg.spring.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface LikeRepository extends JpaRepository<Like, Long> {


}
