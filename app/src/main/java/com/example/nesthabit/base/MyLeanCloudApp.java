package com.example.nesthabit.base;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Message;
import com.example.nesthabit.model.bean.Nest;

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"9NId15Lb59tpyQY3CcCwn2mJ-9Nh9j0Va","YUySEzcH4whFN4h7csvF4bXu");
        AVOSCloud.setDebugLogEnabled(true);
    }

}
