package com.example.nesthabit.model.bean;


import com.avos.avoscloud.AVObject;

import org.litepal.crud.DataSupport;

public class Message extends DataSupport{

    public static final int SIGN = 0;
    public static final int COMMO = 1;

    private long time;
    private String userName;
    private String content;

    public static int getSIGN() {
        return SIGN;
    }

    public static int getCOMMO() {
        return COMMO;
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

    private int type;
}
