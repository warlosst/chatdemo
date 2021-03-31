package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.Room;
import com.celonis.springboot.chatdemo.entity.User;
import com.celonis.springboot.chatdemo.service.RoomService;
import com.celonis.springboot.chatdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomRestController {

    private RoomService roomService;
    private UserService userService;

    @Autowired
    public RoomRestController(RoomService roomService,UserService userService){
        this.roomService = roomService;
        this.userService = userService;
    }

    @GetMapping("/rooms")
    public List<Room> findAllRooms(){
        return roomService.findAll();
    }

    @GetMapping("/rooms/{roomId}")
    public Room findRoomById(@PathVariable int roomId){
        Room room = roomService.findById(roomId);
        if(room == null){
            throw new RuntimeException("Room id not found - "+roomId);
        }
        return room;
    }

    @PostMapping("/rooms")
    public Room saveRoom(@RequestBody Room room){
        room.setId(0);
        roomService.save(room);
        return room;
    }

    @PutMapping("/rooms")
    public Room updateRoom(@RequestBody Room room){
        roomService.save(room);
        return room;
    }

    @DeleteMapping("/rooms/{roomId}")
    public String deleteRoom(@PathVariable int roomId){
        Room room = roomService.findById(roomId);
        if(room == null){
            throw new RuntimeException("Room id not found - "+roomId);
        }
        roomService.deleteById(roomId);
        return "Room id deleted - "+roomId;
    }

    @PutMapping("/rooms/{roomId}/users/{userId}")
    public Room addUserToRoom(@PathVariable int roomId,@PathVariable int userId){
        Room room = roomService.findById(roomId);
        User user = userService.findById(userId);
        if(room==null || user==null){
            throw new RuntimeException("User id or Room id not found");
        }
        room.addUser(user);
        roomService.save(room);
        return room;
    }
    @GetMapping("/rooms/{roomId}/users")
    public List<User> findAllUsersInRoom(@PathVariable int roomId){
        Room room = roomService.findById(roomId);
        if(room==null){
            throw new RuntimeException("User id or Room id not found");
        }
        List<User> userList = room.getUserList();
        return userList;
    }
    @DeleteMapping("/rooms/{roomId}/users/{userId}")
    public String leaveRoom(@PathVariable int roomId,@PathVariable int userId){
        Room room = roomService.findById(roomId);
        User user = userService.findById(userId);
        if(room==null || user==null){
            throw new RuntimeException("User id or Room id not found");
        }
        room.removeUser(user);
        roomService.save(room);
        return "User: "+userId+" left room "+roomId;
    }
}
