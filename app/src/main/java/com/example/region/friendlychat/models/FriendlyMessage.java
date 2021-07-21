package com.example.region.friendlychat.models;

public class FriendlyMessage {
    private String uName;
    private String text;
    private String photoUrl;

    public FriendlyMessage(String uName, String text, String photoUrl) {
        this.uName = uName;
        this.text = text;
        this.photoUrl = photoUrl;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
