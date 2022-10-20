package com.example.finalproject.login;

public class loginResponse {
private  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public loginResponse(String token) {
        this.token = token;
    }
}
