package com.celonis.springboot.chatdemo.dao;

import com.celonis.springboot.chatdemo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findByUsername(String username);

    @Modifying
    @Query("update Client u set u.username = ?1, u.email=?4, u.password = ?2 where u.id = ?3")
    void setUserInfoById(String username, String password, Integer id,String email);
}
