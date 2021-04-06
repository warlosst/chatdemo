package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findById(int id);
    public User findByUsername(String username);
    public void save(User user);
    public void deleteById(int id);
}
