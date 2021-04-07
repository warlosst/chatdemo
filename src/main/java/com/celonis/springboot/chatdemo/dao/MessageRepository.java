package com.celonis.springboot.chatdemo.dao;

import com.celonis.springboot.chatdemo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> findByRoomId(int roomId);
}
