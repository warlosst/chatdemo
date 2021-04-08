package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.entity.Client;

import java.util.List;

public interface ClientService {
    public List<Client> findAll();
    public Client findById(int id);
    public Client findByUsername(String username);
    public void save(Client client);
    public void setClientInfoById(String username, String password, Integer id,String email);
    public void deleteById(int id);
}
