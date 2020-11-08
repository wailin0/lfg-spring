package com.lfg.spring.controller;

import com.lfg.spring.model.Message;
import com.lfg.spring.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class MessageController {

    @Autowired
    private MessageService messageService;

    
    @GetMapping("/messages")
    public List<Message> getAll(){
        return messageService.getAll();
    }

    @GetMapping("/messages/{userId}")
    public ResponseEntity<List<Message>> getByUserId(@PathVariable Long userId){

        return new ResponseEntity<>(messageService.getByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> delete(@PathVariable Long messageId) {

        messageService.delete(messageId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
