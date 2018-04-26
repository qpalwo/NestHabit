package com.example.nesthabit.model.bean;

import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.example.nesthabit.model.DataUtil;

import java.util.List;
import java.util.Observable;


public class Nest extends AVObject{
    public String id;
    public String name;
    public String desc;
    public int membersLimit;
    public long startTime;
    public int challengeDays;
    public String coverImage;
    public int isOpen;
    public long createdTime;
    public String creatorName;
    public String ownerName;
    public int memberAmount;
    public List<AVUser> members;
    public List<Message> signmsgs;
    public List<Message> commumsgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId() {
        this.id = DataUtil.getUnixStamp() + "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(int membersLimit) {
        this.membersLimit = membersLimit;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getChallengeDays() {
        return challengeDays;
    }

    public void setChallengeDays(int challengeDays) {
        this.challengeDays = challengeDays;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreator() {
        return creatorName;
    }

    public void setCreator(String creator) {
        this.creatorName = creator;
    }

    public String getOwner() {
        return ownerName;
    }

    public void setOwner(String owner) {
        this.ownerName = owner;
    }

    public int getMemberAmount() {
        return memberAmount;
    }

    public void setMemberAmount(int memberAmount) {
        this.memberAmount = memberAmount;
    }

    public List<AVUser> getMembers() {
        return members;
    }

    public void setMembers(List<AVUser> members) {
        this.members = members;
    }

    public List<Message> getSignmsgs() {
        return signmsgs;
    }

    public void setSignmsgs(List<Message> signmsgs) {
        this.signmsgs = signmsgs;
    }

    public List<Message> getCommumsgs() {
        return commumsgs;
    }

    public void setCommumsgs(List<Message> commumsgs) {
        this.commumsgs = commumsgs;
    }
}
