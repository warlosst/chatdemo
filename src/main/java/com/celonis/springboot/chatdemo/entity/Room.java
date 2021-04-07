package com.celonis.springboot.chatdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(
            name="membership",
            joinColumns = @JoinColumn(name="room_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> userList;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL
    })
    @JoinColumn(name = "room_id")
    private List<Message> messageList;

    @Column(name="name")
    private String name;

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser(User user){
        if(userList==null){
            userList = new LinkedList<>();
        }
        userList.add(user);
    }

    public void removeUser(User user) {
        userList.remove(user);
    }
}
