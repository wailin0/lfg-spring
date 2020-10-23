package com.lfg.spring.repository;

import com.lfg.spring.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g WHERE g.topic = :topic")
    public List<Group> findByTopic(String topic);
    
}
