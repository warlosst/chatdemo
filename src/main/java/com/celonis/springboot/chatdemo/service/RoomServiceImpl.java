package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.dao.RoomRepository;
import com.celonis.springboot.chatdemo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService{

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(int id) {
        Optional<Room> result = roomRepository.findById(id);
        Room tempRoom = null;
        if(result.isPresent()){
            tempRoom = result.get();
        }
        else{
            throw new RuntimeException("Did not find room with id: "+id);
        }
        return tempRoom;
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void deleteById(int id) {
        Optional<Room> result = roomRepository.findById(id);
        if(result.isPresent()){
            roomRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Did not find room with id: "+id);
        }

    }
}
