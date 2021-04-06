package com.celonis.springboot.chatdemo.dao;

import com.celonis.springboot.chatdemo.entity.Room;
import com.celonis.springboot.chatdemo.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery
                ("from User ",User.class);

        List<User> userList = theQuery.getResultList();
        return userList;
    }

    @Override
    public User findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User user = currentSession.get(User.class,id);
        return user;
    }

    @Override
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery(
                "delete User where id=:userId");
        theQuery.setParameter("userId",id);
        theQuery.executeUpdate();
    }

    @Override
    public User findByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("from User s where s.username=:userName",User.class);
        theQuery.setParameter("userName",username);
        List<User> user = theQuery.getResultList();
        return user.get(0);
    }
}
