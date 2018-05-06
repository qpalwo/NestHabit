package com.example.nesthabit.base;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Message;
import com.example.nesthabit.model.bean.Nest;
import com.squareup.leakcanary.LeakCanary;

import org.litepal.LitePal;

public class MyLeanCloudApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"9NId15Lb59tpyQY3CcCwn2mJ-9Nh9j0Va","YUySEzcH4whFN4h7csvF4bXu");
        AVOSCloud.setDebugLogEnabled(true);
        LeakCanary.install(this);
        LitePal.initialize(this);
    }
    public static Context getContext() {
        return context;
    }

    private void setContext(Context context) {
        this.context = context;
    }


}
