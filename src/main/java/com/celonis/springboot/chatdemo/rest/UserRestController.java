package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.User;
import com.celonis.springboot.chatdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private UserService userService;
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable int userId){
        User user = userService.findById(userId);
        return user;
    }
    @PostMapping
    public User saveUser(@RequestBody User user){
        userService.save(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        System.out.println(principal.getName());
        if(user.getUsername().equals(principal.getName()) ){
        userService.setUserInfoById(user.getUsername(),
                user.getPassword(), user.getId(), user.getEmail());
        }
        else{
            throw new RuntimeException("Cannot update user");
        }
        return user;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId){
        userService.deleteById(userId);
    }

}
