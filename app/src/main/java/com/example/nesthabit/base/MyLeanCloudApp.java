package com.example.nesthabit.base;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"epO0Bbh56YkawM5S9xvSntD4-gzGzoHsz","y5P4hRlvMuMioxIUVHvEGi0O");

        //AVOSCloud.setDebugLogEnabled(true);
    }

}
