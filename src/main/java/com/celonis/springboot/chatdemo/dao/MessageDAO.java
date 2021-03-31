package com.celonis.springboot.chatdemo.dao;

import com.celonis.springboot.chatdemo.entity.Message;

import java.util.List;

public interface MessageDAO {
    public List<Message> findAll();
    public Message findById(int id);
    public void save(Message message);
    public void deleteById(int id);
}
