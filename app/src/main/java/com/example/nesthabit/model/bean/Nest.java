package com.example.nesthabit.model.bean;

import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

import java.util.List;
import java.util.Observable;

@AVClassName("Nest")
public class Nest extends AVObject {
    public static final Parcelable.Creator CREATOR = AVObject.AVObjectCreator.instance;

    public static final String NAME = "name";
    public static final String DESC = "desc";
    public static final String MEMBERS_LIMIT = "members_list";
    public static final String START_TIME = "start_time";
    public static final String CHALLENGE_DAYS = "challenge_days";
    public static final String COVER_IMAGE = "cover_image";
    public static final String OPEN = "open";
    public static final String CREATED_TIME= "created_time";
    public static final String CREATOR1= "creator";
    public static final String OWNER= "owner";
    public static final String MEMBER_AMOUNT= "members_amount";
    public static final String MEMBERS= "members";
    public static final String SIGNMSGS= "signmsgs";
    public static final String COMMUMSGS= "commumsgs";

    public Nest(){

    }



    public void setName(String data) {
        put(NAME, data);
    }

    public void setDesc(String data) {
        put(DESC, data);
    }
    public void setMembersLimit(int data) {
        put(MEMBERS_LIMIT, data);
    }
    public void setStartTime(long data) {
        put(START_TIME, data);
    }

    public void setChallengeDays(int data) {
        put(CHALLENGE_DAYS, data);
    }
    public void setCoverImage(String data) {
        put(COVER_IMAGE, data);
    }

    public void setOpen(int data) {
        put(OPEN, data);
    }

    public void setCreator(AVUser data) {
        put(CREATOR1, data);
    }

    public void setCreatedTime(long data) {
        put(CREATED_TIME, data);
    }

    public void setOwner(AVUser data) {
        put(OWNER, data);
    }

    public void setMemberAmount(int data) {
        put(MEMBER_AMOUNT, data);
    }


    public List<Message> getSingMsgs(){
        return getList(SIGNMSGS, Message.class);
    }

    public List<Message> getCommuMsgs(){
        return getList(COMMUMSGS, Message.class);
    }

    public List<AVUser> getMembers(){
        return getList(MEMBERS, AVUser.class);
    }

    public String getNAME() {
        return getString(NAME);
    }

    public String getDESC() {
        return getString(DESC);
    }

    public int getMembersLimit() {
        return getInt(MEMBERS_LIMIT);
    }

    public long getStartTime() {
        return getLong(START_TIME);
    }

    public int getChallengeDays() {
        return getInt(CHALLENGE_DAYS);
    }

    public String getCoverImage() {
        return getString(COVER_IMAGE);
    }

    public int getOPEN() {
        return getInt(OPEN);
    }

    public long getCreatedTime() {
        return getLong(CREATED_TIME);
    }

    public AVUser getCREATOR1() {
        return getAVUser(getString(CREATOR1));
    }

    public AVUser getOWNER() {
        return getAVUser(getString(OWNER));
    }

    public int getMemberAmount() {
        return getInt(MEMBER_AMOUNT);
    }

}
