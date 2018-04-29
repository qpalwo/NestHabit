package com.example.nesthabit.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Nest;
import com.google.gson.internal.$Gson$Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserHelper {

    public static final String USER_NEST = "user_nest";
    public static final String USER_CLOCK = "user_clock";

    public void addNest(Nest nest){
        AVUser.getCurrentUser().addUnique(USER_NEST, nest);
        AVUser.getCurrentUser().saveInBackground();
    }

    public void addClock(Clock clock){
        AVUser.getCurrentUser().add(USER_CLOCK, clock);
        AVUser.getCurrentUser().saveInBackground();
    }


    public void getNest(AVUser user, CallBack<List<String>> callBack){
        List<String> nests = new ArrayList<>();
        List list = user.getList(USER_NEST);
        for (Object object : list){
            nests.add((String) object);
        }
        callBack.onSuccess(nests);
        callBack.onComplete();
    }

    public void getClock(AVUser user, CallBack<List<String>> callBack){
        List<String> clocks = new ArrayList<>();
        List list = user.getList(USER_CLOCK);
        for (Object object : list){
            clocks.add((String) object);
        }
        callBack.onSuccess(clocks);
        callBack.onComplete();
    }

    public void login(String userName, String passWord, final CallBack<String>callBack){
        AVUser.logInInBackground(userName, passWord, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser user, AVException e) {
                callBack.onSuccess("登陆成功");
                callBack.onComplete();
            }
        });
    }


}
