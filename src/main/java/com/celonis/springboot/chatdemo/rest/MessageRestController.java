package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.Message;
import com.celonis.springboot.chatdemo.entity.MessageHelper;
import com.celonis.springboot.chatdemo.entity.User;
import com.celonis.springboot.chatdemo.rest.exception.NotAuthorizedException;
import com.celonis.springboot.chatdemo.service.MessageService;
import com.celonis.springboot.chatdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private MessageService messageService;
    private UserService userService;
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    public MessageRestController(MessageService messageService,UserService userService){
        this.messageService = messageService;
        this.userService = userService;
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
    public MessageHelper saveMessage(@RequestBody MessageHelper messageHelper,
                                     HttpServletRequest request){
        Principal principal =  request.getUserPrincipal();
        User user = userService.findById(messageHelper.getUserId());
        if(principal.getName().equals(user.getUsername())) {
            messageService.save(messageHelper);
        }
        else{
            throw new NotAuthorizedException("Not authorized");
        }
        return messageHelper;
    }


}
