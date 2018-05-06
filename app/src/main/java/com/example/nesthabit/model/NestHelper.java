package com.example.nesthabit.model;

import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.base.MyLeanCloudApp;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Message;
import com.example.nesthabit.model.bean.Nest;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class NestHelper {
    public static final String NEST_ID = "nest_id";
    public static final String NAME = "name";
    public static final String DESC = "desc";
    public static final String MEMBERS_LIMIT = "members_list";
    public static final String START_TIME = "start_time";
    public static final String CHALLENGE_DAYS = "challenge_days";
    public static final String COVER_IMAGE = "cover_image";
    public static final String IS_OPEN = "is_open";
    public static final String CREATED_TIME= "created_time";
    public static final String CREATOR = "creator";
    public static final String OWNER= "owner";
    public static final String MEMBER_AMOUNT= "members_amount";
    public static final String MEMBERS= "members";
    public static final String SIGNMSGS= "signmsgs";
    public static final String COMMUMSGS= "commumsgs";

    public void createNestOnNet(Nest nest){
//        UserHelper userHelper = new UserHelper();
//        AVObject nest_obj = new AVObject("Nest");
//        nest_obj.put(NEST_ID, nest.getId());
//        nest_obj.put(NAME, nest.getName());
//        nest_obj.put(DESC, nest.getDesc());
//        nest_obj.put(MEMBERS_LIMIT, nest.getMembersLimit());
//        nest_obj.put(START_TIME, nest.getStartTime());
//        nest_obj.put(CHALLENGE_DAYS, nest.getChallengeDays());
//        nest_obj.put(COVER_IMAGE, nest.getCoverImage());
//        nest_obj.put(IS_OPEN, nest.getIsOpen());
//        nest_obj.put(CREATED_TIME, nest.getCreatedTime());
//        nest_obj.put(CREATOR, nest.getCreator());
//        nest_obj.put(OWNER, nest.getOwner());
//        nest_obj.put(MEMBER_AMOUNT, nest.getMemberAmount());
//        nest_obj.put(MEMBERS, nest.getMembers());
//        nest_obj.put(SIGNMSGS, nest.getSignmsgs());
//        nest_obj.put(COMMUMSGS, nest.getCommumsgs());
//        nest_obj.saveInBackground();
//        saveToCache(nest);
//        userHelper.addNest(nest);

        nest.saveAsync();
    }

    public void getNests(List<String> nestId, CallBack<List<Nest>> callBack){
        //final CountDownLatch countDownLatch = new CountDownLatch(nestId.size());
        final List<Nest> nests = DataSupport.findAll(Nest.class);
//        for (String id : nestId){
//            getNest(id, new CallBack<Nest>() {
//                @Override
//                public void onSuccess(Nest data) {
//                    nests.add(data);
//                }
//
//                @Override
//                public void onFailure(String msg) {
//
//                }
//
//                @Override
//                public void onError() {
//
//                }
//
//                @Override
//                public void onComplete() {
//                    countDownLatch.countDown();
//                }
//            });
//        }
//        try {
//            countDownLatch.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        callBack.onSuccess(nests);
        callBack.onComplete();
    }

//    public void getNest(String nestId, final CallBack<Nest> callBack){
//        Nest nest = getFromCache(nestId);
//        if (nest == null){
//            getFromNet(nestId, new CallBack<Nest>() {
//                @Override
//                public void onSuccess(Nest data) {
//                    callBack.onSuccess(data);
//
//                }
//
//                @Override
//                public void onFailure(String msg) {
//                    callBack.onFailure("failure");
//                }
//
//                @Override
//                public void onError() {
//
//                }
//
//                @Override
//                public void onComplete() {
//                    callBack.onComplete();
//                }
//            });
//        }else {
//            callBack.onSuccess(nest);
//            callBack.onComplete();
//        }
//
//    }

    private void saveToCache(final Nest nest) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ACache aCache = ACache.get(MyLeanCloudApp.getContext(), ACache.CACHE_NAME);
                Gson gson = new Gson();
                aCache.put(nest.getId(), gson.toJson(nest, Nest.class), ACache.TIME_DAY);
            }
        }).start();
    }

//    private void getFromNet(String id, final CallBack<Nest> callBack){
//        AVQuery<AVObject> avQuery = new AVQuery<>("Nest");
//        avQuery.whereContains(NEST_ID, id);
//        avQuery.getFirstInBackground(new GetCallback<AVObject>() {
//            @Override
//            public void done(AVObject avObject, AVException e) {
//                Nest nest = new Nest();
//                nest.setStartTime(avObject.getLong(START_TIME));
//                nest.setName(avObject.getString(NAME));
//                nest.setMembersLimit(avObject.getInt(MEMBERS_LIMIT));
//                nest.setMemberAmount(avObject.getInt(MEMBER_AMOUNT));
//                nest.setDesc(avObject.getString(DESC));
//                nest.setCreator(avObject.getString(CREATOR));
//                nest.setCreatedTime(avObject.getLong(CREATED_TIME));
//                nest.setCoverImage(avObject.getString(COVER_IMAGE));
//                nest.setChallengeDays(avObject.getInt(CHALLENGE_DAYS));
//                nest.setId(avObject.getString(NEST_ID));
//                nest.setIsOpen(avObject.getInt(IS_OPEN));
//                nest.setOwner(avObject.getString(OWNER));
//                nest.setSignmsgs(avObject.getList(SIGNMSGS, Message.class));
//                nest.setCommumsgs(avObject.getList(COMMUMSGS, Message.class));
//                callBack.onSuccess(nest);
//                callBack.onComplete();
//            }
//        });
//    }

    private Nest getFromCache(String id) {
        ACache aCache = ACache.get(MyLeanCloudApp.getContext(), ACache.CACHE_NAME);
        Nest nest = (Nest) aCache.getAsObject(id);
        if (nest != null) {
            return nest;
        } else {
            return null;
        }
    }
}
