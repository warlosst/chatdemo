package com.celonis.springboot.chatdemo.dao;

import com.celonis.springboot.chatdemo.entity.Room;

import java.util.List;

public interface RoomDAO {
    public List<Room> findAll();
    public Room findById(int id);
    public void save(Room room);
    public void deleteById(int id);
}
