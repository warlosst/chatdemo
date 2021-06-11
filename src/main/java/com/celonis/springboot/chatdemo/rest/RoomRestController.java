package com.celonis.springboot.chatdemo.rest;

import com.celonis.springboot.chatdemo.entity.Client;
import com.celonis.springboot.chatdemo.entity.Message;
import com.celonis.springboot.chatdemo.entity.Room;
import com.celonis.springboot.chatdemo.service.MessageService;
import com.celonis.springboot.chatdemo.service.RoomService;
import com.celonis.springboot.chatdemo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomRestController {

    private RoomService roomService;
    private ClientService clientService;
    private MessageService messageService;

    @Autowired
    public RoomRestController(RoomService roomService, ClientService clientService, MessageService messageService){
        this.roomService = roomService;
        this.clientService = clientService;
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

    @PutMapping("/{roomId}/clients/{clientId}")
    public Room addUserToRoom(@PathVariable int roomId,@PathVariable int clientId){
        Room room = roomService.findById(roomId);
        Client client = clientService.findById(clientId);
        room.addUser(client);
        roomService.save(room);
        return room;
    }
    @GetMapping("/{roomId}/clients")
    public List<Client> findAllUsersInRoom(@PathVariable int roomId){
        Room room = roomService.findById(roomId);
        List<Client> clientList = room.getUserList();
        return clientList;
    }
    @DeleteMapping("/{roomId}/clients/{clientId}")
    public void leaveRoom(@PathVariable int roomId,@PathVariable int clientId){
        Room room = roomService.findById(roomId);
        Client client = clientService.findById(clientId);
        room.removeUser(client);
        roomService.save(room);
    }
    @GetMapping("/{roomId}/messages")
    public List<Message> getMessagesOfRoom(@PathVariable int roomId){

        List<Message> messageList = messageService.findAllByRoomId(roomId);
        return messageList;
    }
}
