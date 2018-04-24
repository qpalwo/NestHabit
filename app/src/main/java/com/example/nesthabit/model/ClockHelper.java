package com.example.nesthabit.model;

import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.model.bean.Clock;
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
    public static final String OWNER = "owner";
    public static final String TIME_HOUR = "time_hour";
    public static final String TIME_MIN = "time_min";
    public static final String CLOCK_ID = "clock_id";


    public void createClockOnNet(Clock clock, Context context) {
        AVObject clock_obj = new AVObject("Clock");
        clock_obj.put(TITLE, clock.getTitle());
        // TODO: 2018/4/24 添加其它数据
        clock_obj.saveInBackground();
        saveToCache(clock, context);
    }

    private void saveToCache(final Clock clock, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ACache aCache = ACache.get(context, ACache.CACHE_NAME);
                Gson gson = new Gson();
                aCache.put(clock.getId(), gson.toJson(clock, Clock.class));
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
            }, context);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callBack.onSuccess(clocks);
        callBack.onComplete();
    }

    public void getClock(String clockId, final CallBack<Clock> callBack, Context context) {
        Clock clock = getFromCache(clockId, context);
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

    private Clock getFromCache(String id, Context context) {
        ACache aCache = ACache.get(context, ACache.CACHE_NAME);
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
                // TODO: 2018/4/24 nest and owner do not set
                callBack.onSuccess(clock);
                callBack.onComplete();
            }
        });
    }


}
