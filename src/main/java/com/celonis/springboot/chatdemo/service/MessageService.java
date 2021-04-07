package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.entity.Message;
import com.celonis.springboot.chatdemo.entity.MessageHelper;

import java.util.List;

public interface MessageService {
    public List<Message> findAll();
    public Message findById(int id);
    public void save(MessageHelper messageHelper);
    public void deleteById(int id);
    public List<Message> findAllByRoomId(int id);
}
