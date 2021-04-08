package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.dao.ClientRepository;
import com.celonis.springboot.chatdemo.entity.Client;
import com.celonis.springboot.chatdemo.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(int id) {
        Optional<Client> result = clientRepository.findById(id);
        Client tempClient = null;
        if(result.isPresent()){
            tempClient = result.get();
        }
        else{
            throw new NotFoundException("Did not find user with id: "+id);
        }
        return tempClient;
    }

    @Override
    public Client findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Transactional
    @Override
    public void setClientInfoById(String username, String password, Integer id, String email) {
        clientRepository.setUserInfoById(username,password,id,email);
    }

    @Override
    public void deleteById(int id) {
        Optional<Client> result = clientRepository.findById(id);
        if(result.isPresent()) {
            clientRepository.deleteById(id);
        }
        else{
            throw new NotFoundException("Did not find client with id: "+id);
        }
    }
}
