package com.example.nesthabit.model.bean;

import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("Message")
public class Message extends AVObject {
    public static final Parcelable.Creator CREATOR = AVObject.AVObjectCreator.instance;

    public static final String TIME = "time";
    public static final String USER = "user";
    public static final String CONTENT = "content";
    public static final String TYPE= "type";

    public Message(){

    }
}
