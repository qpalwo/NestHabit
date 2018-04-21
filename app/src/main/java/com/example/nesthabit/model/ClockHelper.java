package com.example.nesthabit.model;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Nest;

import java.util.List;


public class ClockHelper {

    public void createClock(String title, String slogan, String music_id, int isOpen,
                            int duration_level, int volume_level, int nap_level,
                            Nest nest, int willing_music, int willing_text,
                            int time_hour, int time_min) {
        Clock clock = new Clock();
        clock.setCreatedTime(DataUtil.getUnixStamp());
        clock.setDurationLevel(duration_level);
        clock.setIsopen(isOpen);
        clock.setMusicId(music_id);
        clock.setNapLevel(nap_level);
        clock.setNest(nest);
        clock.setOwner(AVUser.getCurrentUser());
        clock.setSlogan(slogan);
        clock.setTimeHour(time_hour);
        clock.setTimeMin(time_min);
        clock.setTitle(title);
        clock.setVolumeLevel(volume_level);
        clock.setWillingMusic(willing_music);
        clock.setWillingText(willing_text);
        clock.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Log.d("*****************","success!");
                }
            }
        });
    }



    public void deleteClock(Clock clock){
        try {
            clock.delete();
        } catch (AVException e) {
            e.printStackTrace();
        }
    }

    public void updata(){

    }


}
