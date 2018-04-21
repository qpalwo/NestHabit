package com.example.nesthabit.model.bean;


import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import com.avos.avoscloud.AVUser;

@AVClassName("Clock")
public class Clock extends AVObject {
    public static final Parcelable.Creator CREATOR = AVObject.AVObjectCreator.instance;


    public static final String TITLE = "title";
    public static final String ISOPEN = "isopen";  //0 false 1 true
    public static final String SLOGAN = "slogan";
    public static final String MUSIC_ID = "muscic_id";
    public static final String DURATION_LEVEL = "duration_level";
    public static final String VOLUME_LEVEL = "volume_level";
    public static final String NAP_LEVEL = "nap_level";
    public static final String NEST = "nest";
    public static final String WILLING_MUSIC = "willing_music";  //0 false 1 true
    public static final String WILLING_TEXT = "willing_text";   //0 false 1 true
    public static final String CREATED_TIME = "created_time";
    public static final String OWNER = "owner";
    public static final String TIME_HOUR = "time_hour";
    public static final String TIME_MIN = "time_min";

    public Clock(){

    }


    public void setTitle(String data) {
        put(TITLE, data);
    }

    public void setIsopen(int data) {
        put(ISOPEN, data);
    }

    public void setSlogan(String data) {
        put(SLOGAN, data);
    }

    public void setMusicId(String data) {
        put(MUSIC_ID, data);
    }

    public void setDurationLevel(int data) {
        put(DURATION_LEVEL, data);
    }

    public void setVolumeLevel(int data) {
        put(VOLUME_LEVEL, data);
    }

    public void setNapLevel(int data) {
        put(NAP_LEVEL, data);
    }

    public void setNest(Nest data) {
        put(NEST, data);
    }

    public void setWillingMusic(int data) {
        put(WILLING_MUSIC, data);
    }

    public void setWillingText(int data) {
        put(WILLING_TEXT, data);
    }

    public void setCreatedTime(long data) {
        put(CREATED_TIME, data);
    }

    public void setOwner(AVUser data) {
        put(OWNER, data);
    }

    public void setTimeHour(int data) {
        put(TIME_HOUR, data);
    }

    public void setTimeMin(int data) {
        put(TIME_MIN, data);
    }


    public String getTITLE() {
        return getString(TITLE);
    }

    public int getISONEN() {
        return getInt(ISOPEN);
    }

    public String getSLOGAN() {
        return getString(SLOGAN);
    }

    public String getMusicId() {
        return getString(MUSIC_ID);
    }

    public int getDurationLevel() {
        return getInt(DURATION_LEVEL);
    }

    public int getVolumeLevel() {
        return getInt(VOLUME_LEVEL);
    }

    public int getNapLevel() {
        return getInt(NAP_LEVEL);
    }

    public Nest getNEST() {
        return getAVObject(NEST);
    }

    public int getWillingMusic() {
        return getInt(WILLING_MUSIC);
    }

    public int getWillingText() {
        return getInt(WILLING_TEXT);
    }

    public long getCreatedTime() {
        return getLong(CREATED_TIME);
    }

    public AVUser getOWNER() {
        return getAVUser(OWNER);
    }

    public int getTIME_HOUR() {
        return getInt(TIME_HOUR);
    }

    public int getTIME_MIN() {
        return getInt(TIME_MIN);
    }

}
