package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.Message;
import com.celonis.springboot.chatdemo.entity.MessageHelper;
import com.celonis.springboot.chatdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> findAll(){
        return messageService.findAll();
    }

    @GetMapping("/{messageId}")
    public Message findMessageById(@PathVariable int messageId){
        Message message = messageService.findById(messageId);
        return message;
    }

    @PostMapping
    public MessageHelper saveMessage(@RequestBody MessageHelper messageHelper){

        messageService.save(messageHelper);
        return messageHelper;
    }


    @DeleteMapping("/messages/{messageId}")
    public void deleteMessage(@PathVariable int messageId){
        messageService.deleteById(messageId);
    }
}
