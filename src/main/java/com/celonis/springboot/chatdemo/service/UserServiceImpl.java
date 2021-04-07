package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.dao.UserRepository;
import com.celonis.springboot.chatdemo.entity.Room;
import com.celonis.springboot.chatdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User findById(int id) {
        Optional<User> result = userRepository.findById(id);
        User tempUser = null;
        if(result.isPresent()){
            tempUser = result.get();
        }
        else{
            throw new RuntimeException("Did not find user with id: "+id);
        }
        return tempUser;
    }
    @Transactional
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
            userRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Did not find user with id: "+id);
        }
    }
}
