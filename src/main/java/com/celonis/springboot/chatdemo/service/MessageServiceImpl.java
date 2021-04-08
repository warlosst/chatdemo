package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.dao.MessageRepository;
import com.celonis.springboot.chatdemo.dao.RoomRepository;
import com.celonis.springboot.chatdemo.dao.UserRepository;
import com.celonis.springboot.chatdemo.entity.Message;
import com.celonis.springboot.chatdemo.entity.MessageHelper;
import com.celonis.springboot.chatdemo.entity.Room;
import com.celonis.springboot.chatdemo.entity.User;
import com.celonis.springboot.chatdemo.rest.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              UserRepository userRepository, RoomRepository roomRepository){
        this.messageRepository = messageRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }


    @Override
    public Message findById(int id) {
        Optional<Message> result = messageRepository.findById(id);
        Message tempMessage = null;
        if(result.isPresent()){
            tempMessage = result.get();
        }
        else{
            throw new RuntimeException("Did not find message with id: "+id);
        }
        return tempMessage;
    }


    @Override
    public void save(MessageHelper messageHelper) {
        Optional<User> resultUser = userRepository.findById(messageHelper.getUserId());
        Optional<Room> resultRoom = roomRepository.findById(messageHelper.getRoomId());
        String messageText = messageHelper.getMessage();
        User user = null;
        Room room = null;
        if(resultUser.isPresent()){
           user = resultUser.get();
        }
        else{
            throw new NotFoundException("No user with this id");
        }
        if(resultRoom.isPresent()){
            room = resultRoom.get();
        }
        Message message = new Message(messageText,user,room);
        messageRepository.save(message);
    }

    public List<Message> findAllByRoomId(int roomId)
    {
        List<Message> messages =
                messageRepository.findByRoomId(roomId);

        return messages;

    }


    @Override
    public void deleteById(int id) {
        Optional<Message> result = messageRepository.findById(id);
        if(result.isPresent()) {
            messageRepository.deleteById(id);
        }
        else{
            throw new NotFoundException("Did not find message with id: "+id);
        }
    }

}
