package com.celonis.springboot.chatdemo.utils;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String email;

    public UserDTO( String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
