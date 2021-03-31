package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.dao.RoomDAO;
import com.celonis.springboot.chatdemo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService{

    private RoomDAO roomDAO;

    @Autowired
    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
    @Transactional
    @Override
    public List<Room> findAll() {
        return roomDAO.findAll();
    }

    @Transactional
    @Override
    public Room findById(int id) {
        return roomDAO.findById(id);
    }

    @Transactional
    @Override
    public void save(Room room) {
        roomDAO.save(room);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        roomDAO.deleteById(id);
    }
}
