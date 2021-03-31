package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.entity.Message;

import java.util.List;

public interface MessageService {
    public List<Message> findAll();
    public Message findById(int id);
    public void save(Message message);
    public void deleteById(int id);
}
