package com.example.nesthabit.model.bean;


import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.example.nesthabit.model.DataUtil;

public class Clock extends AVObject{
    public String id;
    public String title;
    public int isOpen;
    public String slogan;
    public String musicId;
    public int durationLevel;
    public int volumeLevel;
    public int napLevel;
    public Nest nest;
    public int willingMusic;
    public int willingText;
    public long createTime;
    public String ownerName;
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

    public int getNapLevel() {
        return napLevel;
    }

    public void setNapLevel(int mapLevel) {
        this.napLevel = mapLevel;
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

    public String getOwner() {
        return ownerName;
    }

    public void setOwner(String owner) {
        this.ownerName = owner;
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
