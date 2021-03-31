package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.Message;
import com.celonis.springboot.chatdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageRestController {

    private MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public List<Message> findAll(){
        return messageService.findAll();
    }

    @GetMapping("/messages/{messageId}")
    public Message findMessageById(@PathVariable int messageId){
        Message message = messageService.findById(messageId);
        if(message == null){
            throw new RuntimeException("Message id not found - "+messageId);
        }
        return message;
    }

    @PostMapping("/messages")
    public Message saveMessage(@RequestBody Message message){
        message.setId(0);
        messageService.save(message);
        return message;
    }

    @PutMapping("/messages")
    public Message updateMessage(@RequestBody Message message){
        messageService.save(message);
        return message;
    }

    @DeleteMapping("/messages/{messageId}")
    public String deleteMessage(@PathVariable int messageId){
        Message message = messageService.findById(messageId);
        if(message == null){
            throw new RuntimeException("Message id not found - "+messageId);

        }
        messageService.deleteById(messageId);
        return "Message id deleted - "+messageId;
    }
}
