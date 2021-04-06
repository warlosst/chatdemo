package com.celonis.springboot.chatdemo.dao;

import com.celonis.springboot.chatdemo.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> findAll();
    public User findById(int id);
    public void save(User user);
    public void deleteById(int id);

    public User findByUsername(String username);
}
