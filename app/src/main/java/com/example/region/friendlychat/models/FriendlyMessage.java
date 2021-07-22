package com.example.region.friendlychat.models;

public class FriendlyMessage {
    String message;
    long msgTime;
    String uId;

    public FriendlyMessage(String message, String uId,long msgTime) {
        this.message = message;
        this.msgTime = msgTime;
        this.uId = uId;
    }

    public FriendlyMessage(String message, String uId) {
        this.message = message;
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public FriendlyMessage(){

    }
}
