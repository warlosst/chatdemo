package com.celonis.springboot.chatdemo.entity;

public class AuthenticationResponse {
    private final String jwt;
    private final int id;

    public AuthenticationResponse(String jwt, int id) {
        this.jwt = jwt;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getJwt() {
        return jwt;
    }
}
