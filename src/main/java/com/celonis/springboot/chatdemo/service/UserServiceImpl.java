package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.dao.UserRepository;
import com.celonis.springboot.chatdemo.entity.User;
import com.celonis.springboot.chatdemo.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> result = userRepository.findById(id);
        User tempUser = null;
        if(result.isPresent()){
            tempUser = result.get();
        }
        else{
            throw new NotFoundException("Did not find user with id: "+id);
        }
        return tempUser;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void setUserInfoById(String username, String password, Integer id, String email) {
        userRepository.setUserInfoById(username,password,id,email);
    }

    @Override
    public void deleteById(int id) {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
            userRepository.deleteById(id);
        }
        else{
            throw new NotFoundException("Did not find user with id: "+id);
        }
    }
}
