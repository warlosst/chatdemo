package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.Client;
import com.celonis.springboot.chatdemo.rest.exception.NotAuthorizedException;
import com.celonis.springboot.chatdemo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private ClientService clientService;
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    @Autowired
    public ClientRestController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> findAllClients(){
        return clientService.findAll();
    }

    @GetMapping("/{clientId}")
    public Client findClientById(@PathVariable int clientId){
        Client client = clientService.findById(clientId);
        return client;
    }
    @PostMapping
    public Client saveClient(@Valid @RequestBody Client client){
        clientService.save(client);
        return client;
    }

    @PutMapping
    public Client updateClient(@Valid @RequestBody Client client, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        if(client.getUsername().equals(principal.getName()) ){
        clientService.setClientInfoById(client.getUsername(),
                client.getPassword(), client.getId(), client.getEmail());
        }
        else {
            throw new NotAuthorizedException("Not authorized");
        }
        return client;
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable int clientId,HttpServletRequest request){
//        Principal principal = request.getUserPrincipal();
//        Client client = clientService.findById(clientId);
//        if(principal.getName().equals(client.getUsername())) {
//            clientService.deleteById(clientId);
//        }
//        else{
//            throw new NotAuthorizedException("Not authorized");
//        }
        clientService.deleteById(clientId);
    }

}
