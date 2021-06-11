package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.Client;
import com.celonis.springboot.chatdemo.entity.Message;
import com.celonis.springboot.chatdemo.entity.MessageHelper;
import com.celonis.springboot.chatdemo.service.ClientService;
import com.celonis.springboot.chatdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private MessageService messageService;
    private ClientService clientService;
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    public MessageRestController(MessageService messageService, ClientService clientService){
        this.messageService = messageService;
        this.clientService = clientService;
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
//        Principal principal =  request.getUserPrincipal();
        Client client = clientService.findById(messageHelper.getUserId());
//        if(principal.getName().equals(client.getUsername())) {
            messageService.save(messageHelper);
//        }
////        else{
////            throw new NotAuthorizedException("Not authorized");
////        }
        return messageHelper;
    }


}
