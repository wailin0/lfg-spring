package com.lfg.spring.service;

import java.util.List;

import com.lfg.spring.model.Message;
import com.lfg.spring.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    public Message create(Long toUser, Long fromUser, String content){
        
        return messageRepository.save(Message.builder()
            .toUser(userService.getReference(toUser))
            .fromUser(userService.getReference(fromUser))
            .content(content).build());
    }

    public List<Message> getByUserId(Long userId){

        return messageRepository.findByUserId(userId);
    }

    public void delete(Long messageId){

        messageRepository.deleteById(messageId);;
    }
}
