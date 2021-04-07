package com.celonis.springboot.chatdemo.entity;

public class MessageHelper {
    private String message;
    private int roomId;
    private int userId;

    public MessageHelper(String message, int roomId, int userId) {
        this.message = message;
        this.roomId = roomId;
        this.userId = userId;
    }

    public MessageHelper() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
