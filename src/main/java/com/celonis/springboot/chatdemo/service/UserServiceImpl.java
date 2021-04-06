package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.dao.UserDAO;
import com.celonis.springboot.chatdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Transactional
    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }
    @Transactional
    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }
}
