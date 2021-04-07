package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.Message;
import com.celonis.springboot.chatdemo.entity.Room;
import com.celonis.springboot.chatdemo.entity.User;
import com.celonis.springboot.chatdemo.service.MessageService;
import com.celonis.springboot.chatdemo.service.RoomService;
import com.celonis.springboot.chatdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomRestController {

    private RoomService roomService;
    private UserService userService;
    private MessageService messageService;

    @Autowired
    public RoomRestController(RoomService roomService,UserService userService,MessageService messageService){
        this.roomService = roomService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping
    public List<Room> findAllRooms(){
        return roomService.findAll();
    }

    @GetMapping("/{roomId}")
    public Room findRoomById(@PathVariable int roomId){
        Room room = roomService.findById(roomId);
        return room;
    }

    @PostMapping
    public Room saveRoom(@RequestBody Room room){
        roomService.save(room);
        return room;
    }

    @PutMapping
    public Room updateRoom(@RequestBody Room room){
        roomService.save(room);
        return room;
    }

    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable int roomId){
        roomService.deleteById(roomId);
    }

    @PutMapping("/{roomId}/users/{userId}")
    public Room addUserToRoom(@PathVariable int roomId,@PathVariable int userId){
        Room room = roomService.findById(roomId);
        User user = userService.findById(userId);
        room.addUser(user);
        roomService.save(room);
        return room;
    }
    @GetMapping("/{roomId}/users")
    public List<User> findAllUsersInRoom(@PathVariable int roomId){
        Room room = roomService.findById(roomId);
        List<User> userList = room.getUserList();
        return userList;
    }
    @DeleteMapping("/{roomId}/users/{userId}")
    public void leaveRoom(@PathVariable int roomId,@PathVariable int userId){
        Room room = roomService.findById(roomId);
        User user = userService.findById(userId);
        room.removeUser(user);
        roomService.save(room);
    }
    @GetMapping("/{roomId}/messages")
    public List<Message> getMessagesOfRoom(@PathVariable int roomId){

        List<Message> messageList = messageService.findAllByRoomId(roomId);
        return messageList;
    }
}
