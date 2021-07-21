package com.example.region.friendlychat.models;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {
    String user_name, profile_pic, mail, password, uid,status;


    public User(String user_name, String profile_pic, String mail, String password, String uid) {
        this.user_name = user_name;
        this.profile_pic = profile_pic;
        this.mail = mail;
        this.password = password;
        this.uid = uid;
    }
    public User(){

    }
    //sign-up contruct :
    public User(String user_name, String mail, String password) {
        this.user_name = user_name;
        this.mail = mail;
        this.password = password;
    }

    protected User(Parcel in) {
        user_name = in.readString();
        profile_pic = in.readString();
        mail = in.readString();
        password = in.readString();
        uid = in.readString();
        status = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_name);
        dest.writeString(profile_pic);
        dest.writeString(mail);
        dest.writeString(password);
        dest.writeString(uid);
        dest.writeString(status);
    }
}
