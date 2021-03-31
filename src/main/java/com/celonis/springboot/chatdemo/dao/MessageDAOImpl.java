package com.celonis.springboot.chatdemo.dao;

import com.celonis.springboot.chatdemo.entity.Message;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MessageDAOImpl implements MessageDAO{

    private EntityManager entityManager;

    @Autowired
    public MessageDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Message> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery
                ("from Message ",Message.class);

        List<Message> messageList = theQuery.getResultList();
        return messageList;
    }

    @Override
    public Message findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Message message = currentSession.get(Message.class,id);
        return message;
    }

    @Override
    public void save(Message message) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(message);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery(
                "delete Message where id=:messageId");
        theQuery.setParameter("messageId",id);
        theQuery.executeUpdate();
    }
}
