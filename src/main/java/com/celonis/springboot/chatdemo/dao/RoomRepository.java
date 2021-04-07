package com.celonis.springboot.chatdemo.dao;

import com.celonis.springboot.chatdemo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {
}
