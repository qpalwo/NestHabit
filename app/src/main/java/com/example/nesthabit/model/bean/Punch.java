package com.example.nesthabit.model.bean;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class Punch extends DataSupport {

    private int id;
    private int userId;
    private int nestId;
    private int allPunch;
    private int successPunch;
    private long lastPunchDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNestId() {
        return nestId;
    }

    public void setNestId(int nestId) {
        this.nestId = nestId;
    }

    public int getAllPunch() {
        return allPunch;
    }

    public void setAllPunch(int allPunch) {
        this.allPunch = allPunch;
    }

    public int getSuccessPunch() {
        return successPunch;
    }

    public void setSuccessPunch(int successPunch) {
        this.successPunch = successPunch;
    }

    public long getLastPunchDate() {
        return lastPunchDate;
    }

    public void setLastPunchDate(long lastPunchDate) {
        this.lastPunchDate = lastPunchDate;
    }
}
