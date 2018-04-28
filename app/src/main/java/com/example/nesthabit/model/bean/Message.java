package com.example.nesthabit.model.bean;


import com.avos.avoscloud.AVObject;

public class Message extends AVObject{

    public static final int SIGN = 0;
    public static final int COMMO = 1;

    public long time;
    public String userName;
    public String content;
    public int type;
}
