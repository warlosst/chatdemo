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
    @Column(name = "room_id")
    private int id;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(
            name="membership",
            joinColumns = @JoinColumn(name="room_id"),
            inverseJoinColumns = @JoinColumn(name="client_id")
    )
    private List<Client> clientList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL
    })
    @JoinColumn(name = "room_id")
    private List<Message> messageList;

    @Column(name="room_name")
    private String name;

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Client> getUserList() {
        return clientList;
    }

    public void setUserList(List<Client> clientList) {
        this.clientList = clientList;
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

    public void addUser(Client client){
        if(clientList ==null){
            clientList = new LinkedList<>();
        }
        clientList.add(client);
    }

    public void removeUser(Client client) {
        clientList.remove(client);
    }
}
