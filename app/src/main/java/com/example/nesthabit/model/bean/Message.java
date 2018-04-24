package com.example.nesthabit.model.bean;

import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;


public class Message{

    public static final int SIGN = 0;
    public static final int COMMO = 1;

    public long time;
    public AVUser user;
    public String content;
    public int type;
}
