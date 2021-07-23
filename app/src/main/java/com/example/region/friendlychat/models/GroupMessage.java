package com.example.region.friendlychat.models;

public class GroupMessage {
    String uName;
    String message;
    String uId;

    public GroupMessage(String uName, String message, String uId) {
        this.uName = uName;
        this.message = message;
        this.uId = uId;
    }
    public GroupMessage(){

    }
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
