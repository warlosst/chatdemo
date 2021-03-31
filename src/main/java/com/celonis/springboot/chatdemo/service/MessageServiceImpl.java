package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.dao.MessageDAO;
import com.celonis.springboot.chatdemo.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService{

    private MessageDAO messageDAO;
    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    @Transactional
    @Override
    public List<Message> findAll() {
        return messageDAO.findAll();
    }

    @Transactional
    @Override
    public Message findById(int id) {
        return messageDAO.findById(id);
    }

    @Transactional
    @Override
    public void save(Message message) {
        messageDAO.save(message);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        messageDAO.deleteById(id);
    }

}
