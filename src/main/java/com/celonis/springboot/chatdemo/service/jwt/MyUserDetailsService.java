package com.celonis.springboot.chatdemo.service.jwt;

import com.celonis.springboot.chatdemo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private ClientService clientService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new User(clientService.findByUsername(username).getUsername(),
                clientService.findByUsername(username).getPassword(),new ArrayList<>());
    }
}
