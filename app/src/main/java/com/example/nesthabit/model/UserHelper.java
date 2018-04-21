package com.example.nesthabit.model;

import com.avos.avoscloud.AVUser;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Nest;

import java.util.List;

public class UserHelper {

    public static final String USER_NEST = "user_nest";
    public static final String USER_CLOCK = "user_clock";

    public void getNest(AVUser user, CallBack<List<Nest>> callBack){
        List<Nest> nests = user.getList(USER_NEST, Nest.class);
        callBack.onSuccess(nests);
        callBack.onComplete();
    }

    public void getClock(AVUser user, CallBack<List<Clock>> callBack){
        List<Clock> clocks = user.getList(USER_CLOCK, Clock.class);
        callBack.onSuccess(clocks);
        callBack.onComplete();
    }


}
