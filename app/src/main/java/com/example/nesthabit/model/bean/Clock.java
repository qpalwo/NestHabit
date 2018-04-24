package com.example.nesthabit.model.bean;


import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import com.avos.avoscloud.AVUser;
import com.example.nesthabit.model.DataUtil;

public class Clock {
    public String id;
    public String title;
    public int isOpen;
    public String slogan;
    public String musicId;
    public int durationLevel;
    public int volumeLevel;
    public int mapLevel;
    public Nest nest;
    public int willingMusic;
    public int willingText;
    public long createTime;
    public AVUser owner;
    public int timeHour;
    public int timeMin;

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setId() {
        this.id = DataUtil.getUnixStamp() + "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public int getDurationLevel() {
        return durationLevel;
    }

    public void setDurationLevel(int durationLevel) {
        this.durationLevel = durationLevel;
    }

    public int getVolumeLevel() {
        return volumeLevel;
    }

    public void setVolumeLevel(int volumeLevel) {
        this.volumeLevel = volumeLevel;
    }

    public int getMapLevel() {
        return mapLevel;
    }

    public void setNapLevel(int mapLevel) {
        this.mapLevel = mapLevel;
    }

    public Nest getNest() {
        return nest;
    }

    public void setNest(Nest nest) {
        this.nest = nest;
    }

    public int getWillingMusic() {
        return willingMusic;
    }

    public void setWillingMusic(int willingMusic) {
        this.willingMusic = willingMusic;
    }

    public int getWillingText() {
        return willingText;
    }

    public void setWillingText(int willingText) {
        this.willingText = willingText;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public AVUser getOwner() {
        return owner;
    }

    public void setOwner(AVUser owner) {
        this.owner = owner;
    }

    public int getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(int timeHour) {
        this.timeHour = timeHour;
    }

    public int getTimeMin() {
        return timeMin;
    }

    public void setTimeMin(int timeMin) {
        this.timeMin = timeMin;
    }




}
