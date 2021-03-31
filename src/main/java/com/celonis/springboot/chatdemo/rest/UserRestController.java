package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.User;
import com.celonis.springboot.chatdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public User findUserById(@PathVariable int userId){
        User user = userService.findById(userId);
        if(user ==null){
            throw new RuntimeException("User id not found - "+userId);
        }
        return user;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        user.setId(0);
        userService.save(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        userService.save(user);
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId){
        User user = userService.findById(userId);
        if(user == null){
            throw new RuntimeException("User id not found - "+userId);
        }
        userService.deleteById(userId);
        return "User id deleted - "+userId;
    }
}
