package com.play.breed.util.rxbus.busEvent;

public class RegisterEvent {
    String userName;

    public RegisterEvent(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
