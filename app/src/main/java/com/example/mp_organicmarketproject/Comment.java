package com.example.mp_organicmarketproject;

public class Comment {
    private String comment;
    private String userInfo;

    public Comment() {

    }

    public Comment(String comment, String userInfo) {
        this.comment = comment;
        this.userInfo = userInfo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}


