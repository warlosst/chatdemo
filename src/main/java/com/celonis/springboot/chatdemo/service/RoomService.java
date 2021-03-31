package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.entity.Room;

import java.util.List;

public interface RoomService {
    public List<Room> findAll();
    public Room findById(int id);
    public void save(Room room);
    public void deleteById(int id);
}
