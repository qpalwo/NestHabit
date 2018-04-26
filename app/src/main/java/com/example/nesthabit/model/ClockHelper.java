package com.example.nesthabit.model;

import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.base.MyLeanCloudApp;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Nest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class ClockHelper {

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
    public static final String OWNER_NAME = "owner_name";
    public static final String TIME_HOUR = "time_hour";
    public static final String TIME_MIN = "time_min";
    public static final String CLOCK_ID = "clock_id";


    public void createClockOnNet(Clock clock) {
        AVObject clock_obj = new AVObject("Clock");
        clock_obj.put(TITLE, clock.getTitle());
        clock_obj.put(ISOPEN, clock.getIsOpen());
        clock_obj.put(SLOGAN, clock.getSlogan());
        clock_obj.put(MUSIC_ID, clock.getMusicId());
        clock_obj.put(DURATION_LEVEL, clock.getDurationLevel());
        clock_obj.put(VOLUME_LEVEL, clock.getVolumeLevel());
        clock_obj.put(NAP_LEVEL, clock.getNapLevel());
        clock_obj.put(NEST, clock.getNest());
        clock_obj.put(WILLING_MUSIC, clock.getWillingMusic());
        clock_obj.put(WILLING_TEXT, clock.getWillingText());
        clock_obj.put(CREATED_TIME, clock.getCreateTime());
        clock_obj.put(TIME_HOUR, clock.getTimeHour());
        clock_obj.put(TIME_MIN, clock.getTimeMin());
        clock_obj.put(CLOCK_ID, clock.getId());
        clock_obj.put(OWNER_NAME, clock.getOwner());
        clock_obj.saveInBackground();
        saveToCache(clock);
    }

    private void saveToCache(final Clock clock) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ACache aCache = ACache.get(MyLeanCloudApp.getContext(), ACache.CACHE_NAME);
                Gson gson = new Gson();
                aCache.put(clock.getId(), gson.toJson(clock, Clock.class), ACache.TIME_DAY);
            }
        }).start();
    }

    public void getClocks(List<String> clockId, CallBack<List<Clock>> callBack, Context context) {
        final CountDownLatch countDownLatch = new CountDownLatch(clockId.size());
        final List<Clock> clocks = new ArrayList<>();
        for(String id : clockId){
            getClock(id, new CallBack<Clock>() {
                @Override
                public void onSuccess(Clock data) {
                    clocks.add(data);
                }

                @Override
                public void onFailure(String msg) {

                }

                @Override
                public void onError() {

                }

                @Override
                public void onComplete() {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callBack.onSuccess(clocks);
        callBack.onComplete();
    }

    public void getClock(String clockId, final CallBack<Clock> callBack) {
        Clock clock = getFromCache(clockId);
        if (clock == null) {
            getFromNet(clockId, new CallBack<Clock>() {
                @Override
                public void onSuccess(Clock data) {
                    callBack.onSuccess(data);
                }

                @Override
                public void onFailure(String msg) {

                }

                @Override
                public void onError() {

                }

                @Override
                public void onComplete() {
                    callBack.onComplete();
                }
            });
        }else {
            callBack.onSuccess(clock);
            callBack.onComplete();
        }

    }

    private Clock getFromCache(String id) {
        ACache aCache = ACache.get(MyLeanCloudApp.getContext(), ACache.CACHE_NAME);
        Clock clock = (Clock) aCache.getAsObject(id);
        if (clock != null) {
            return clock;
        } else {
            return null;
        }
    }

    private void getFromNet(final String id, final CallBack<Clock> callBack) {
        AVQuery<AVObject> avQuery = new AVQuery<>("Clock");
        avQuery.whereContains(CLOCK_ID, id);
        avQuery.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                Clock clock = new Clock();
                clock.setWillingText(avObject.getInt(WILLING_TEXT));
                clock.setWillingMusic(avObject.getInt(WILLING_MUSIC));
                clock.setVolumeLevel(avObject.getInt(VOLUME_LEVEL));
                clock.setTitle(avObject.getString(TITLE));
                clock.setTimeMin(avObject.getInt(TIME_MIN));
                clock.setTimeHour(avObject.getInt(TIME_HOUR));
                clock.setSlogan(avObject.getString(SLOGAN));
                clock.setMusicId(avObject.getString(MUSIC_ID));
                clock.setDurationLevel(avObject.getInt(DURATION_LEVEL));
                clock.setCreateTime(avObject.getLong(CREATED_TIME));
                clock.setNapLevel(avObject.getInt(NAP_LEVEL));
                clock.setIsOpen(avObject.getInt(ISOPEN));
                clock.setId(avObject.getString(CLOCK_ID));
                clock.setOwner(avObject.getString(OWNER_NAME));
                try {
                    clock.setNest(avObject.getAVObject(NEST, Nest.class));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                callBack.onSuccess(clock);
                callBack.onComplete();
            }
        });

    }


}
