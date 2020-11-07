package com.lfg.spring.repository;

import javax.transaction.Transactional;

import com.lfg.spring.model.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> { }
