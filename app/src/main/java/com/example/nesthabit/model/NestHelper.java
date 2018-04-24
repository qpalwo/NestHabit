package com.example.nesthabit.model;

import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Message;
import com.example.nesthabit.model.bean.Nest;
import com.google.gson.Gson;

import java.util.List;

public class NestHelper {

    public static final String NAME = "name";
    public static final String DESC = "desc";
    public static final String MEMBERS_LIMIT = "members_list";
    public static final String START_TIME = "start_time";
    public static final String CHALLENGE_DAYS = "challenge_days";
    public static final String COVER_IMAGE = "cover_image";
    public static final String IS_OPEN = "is_open";
    public static final String CREATED_TIME= "created_time";
    public static final String CREATOR1= "creator";
    public static final String OWNER= "owner";
    public static final String MEMBER_AMOUNT= "members_amount";
    public static final String MEMBERS= "members";
    public static final String SIGNMSGS= "signmsgs";
    public static final String COMMUMSGS= "commumsgs";

    public void createNestOnNet(Nest nest, Context context){
        AVObject nest_obj = new AVObject("Clock");
        nest_obj.put(NAME, nest.getName());
        // TODO: 2018/4/24 添加其它数据

        nest_obj.saveInBackground();
        saveToCache(nest, context);

    }

    private void saveToCache(final Nest nest, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ACache aCache = ACache.get(context, ACache.CACHE_NAME);
                Gson gson = new Gson();
                aCache.put(nest.getId(), gson.toJson(nest, Clock.class));
            }
        }).start();
    }

    private Nest getFromCache(String id, Context context) {
        ACache aCache = ACache.get(context, ACache.CACHE_NAME);
        Nest nest = (Nest) aCache.getAsObject(id);
        if (nest != null) {
            return nest;
        } else {
            return null;
        }
    }
}
