package com.example.nesthabit.model.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Nest extends DataSupport implements Serializable{
    private int id;
    private String name;
    private String desc;
    private int membersLimit;
    private long startTime;
    private int challengeDays;
    private String coverImage;
    private int isOpen;
    private long createdTime;
    private String creatorName;
    private String ownerName;
    private int memberAmount;
    private List<User> members = new ArrayList<>();
    private List<Message> signmsgs = new ArrayList<>();
    private List<Message> commumsgs = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public void setId() {
//        this.id = DateUtil.getUnixStamp() + "";
//    }

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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
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
