package com.lfg.spring.service;

import com.lfg.spring.model.Message;
import com.lfg.spring.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {


    @Autowired
    private MessageRepository messageRepository;

    public Message save(Message message){
        
        return messageRepository.save(message);
    }
}
