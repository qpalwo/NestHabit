package com.example.nesthabit.model.bean;


import com.avos.avoscloud.AVObject;

import org.litepal.crud.DataSupport;

public class Message extends DataSupport{

    public static final int SIGN = 0;
    public static final int COMMON = 1;
    public static final int OWN = 2;
    public static final int OTHER = 3;

    private int id;
    private int type;
    private int userId;
    private int nestId;
    private long time;
    private String userName;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNestId() {
        return nestId;
    }

    public void setNestId(int nestId) {
        this.nestId = nestId;
    }
}
