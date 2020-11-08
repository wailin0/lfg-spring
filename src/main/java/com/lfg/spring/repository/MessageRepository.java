package com.lfg.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.lfg.spring.model.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.toUser.id = :userId OR m.fromUser.id = :userId")
    public List<Message> findByUserId(Long userId);
 }
