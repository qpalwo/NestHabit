package com.example.nesthabit.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.nesthabit.model.ACache;
import com.example.nesthabit.model.DateUtil;
import com.example.nesthabit.model.bean.Clock;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.nesthabit.activity.NestContentActivity.NEST;

public class AlarmSetManager {
    private static final String TAG = "AlarmSetManager";
    public static final String ALARM_ACTION = "unique.NestHabit.action.CLOCK_REMIND";

    private static class InstanceHolder {
        public static AlarmSetManager alarmSetManager = new AlarmSetManager();
    }

    private class AlarmInfo {
        public long time;
        public boolean[] days;
    }

    private AlarmSetManager() {
    }

    private List<Long> alarmTime;

    public AlarmSetManager getManager() {
        alarmTime = new ArrayList<>();
        return InstanceHolder.alarmSetManager;
    }

    public void initManager(Context context) {
        ACache aCache = ACache.get(context, ACache.CACHE_NAME);
        String json = aCache.getAsString("alarm_time");
        Gson gson = new Gson();
        alarmTime = gson.fromJson(json, new TypeToken<List<AlarmInfo>>() {
        }.getType());
    }

    private void checkList() {
        if (alarmTime.size() > 0) {
            long currentTime = DateUtil.getUnixStamp();
        }
    }

    public static void setAlarm(Context context) {
        Clock clock = DateUtil.getNextClock();
        Intent intent = new Intent();
        intent.setClassName("com.example.nesthabit",
                "com.example.nesthabit.broadcast.ClockRemindReceiver");
        long time = Long.MAX_VALUE;
        if (clock != null) {
            time = DateUtil.getNextClockTime(clock);
            intent.putExtra("title", clock.getTitle());
            Log.d(TAG, "setAlarm: " + clock.getTitle());
            intent.putExtra("time", time);
            intent.putExtra("isVibrate", clock.getIsVibrate() == 1);
            intent.putExtra("sound", clock.getMusicId());
            intent.putExtra(NEST, clock.getNestId());
        }
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (am != null && clock != null) {
            am.setExact(AlarmManager.RTC_WAKEUP, time * 1000, pi);
//            Log.d(TAG, "addClock: " + String.valueOf(DateUtil.getUnixStamp())
//                    + "\n" + String.valueOf(DateUtil.getNextClockTime()));
        }
    }

    private class AlarmManagerUtil {

        public void setAlarmTime(Context context, long timeInMillis, Intent intent) {
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent sender = PendingIntent.getBroadcast(context, intent.getIntExtra("id", 0),
                    intent, PendingIntent.FLAG_CANCEL_CURRENT);
            int interval = (int) intent.getLongExtra("intervalMillis", 0);
            if (am != null)
                am.setWindow(AlarmManager.RTC_WAKEUP, timeInMillis, interval, sender);

        }

        public void cancelAlarm(Context context, String action, int id) {
            Intent intent = new Intent(action);
            PendingIntent pi = PendingIntent.getBroadcast(context, id, intent, PendingIntent
                    .FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (am != null)
                am.cancel(pi);
        }

        /**
         * @param flag            周期性时间间隔的标志,flag = 0 表示一次性的闹钟, flag = 1 表示每天提醒的闹钟(1天的时间间隔),flag = 2
         *                        表示按周每周提醒的闹钟（一周的周期性时间间隔）
         * @param hour            时
         * @param minute          分
         * @param id              闹钟的id
         * @param week            week=0表示一次性闹钟或者按天的周期性闹钟，非0 的情况下是几就代表以周为周期性的周几的闹钟
         * @param tips            闹钟提示信息
         * @param soundOrVibrator 2表示声音和震动都执行，1表示只有铃声提醒，0表示只有震动提醒
         */
        public void setAlarm(Context context, int flag, int hour, int minute, int id, int
                week, String tips, int soundOrVibrator) {
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            long intervalMillis = 0;
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                    (Calendar.DAY_OF_MONTH), hour, minute, 10);
            if (flag == 0) {
                intervalMillis = 0;
            } else if (flag == 1) {
                intervalMillis = 24 * 3600 * 1000;
            } else if (flag == 2) {
                intervalMillis = 24 * 3600 * 1000 * 7;
            }
            Intent intent = new Intent(ALARM_ACTION);
            intent.putExtra("intervalMillis", intervalMillis);
            intent.putExtra("msg", tips);
            intent.putExtra("id", id);
            intent.putExtra("soundOrVibrator", soundOrVibrator);
            PendingIntent sender = PendingIntent.getBroadcast(context, id, intent, PendingIntent
                    .FLAG_CANCEL_CURRENT);
            if (am != null)
                am.setWindow(AlarmManager.RTC_WAKEUP, calMethod(week, calendar.getTimeInMillis()),
                        intervalMillis, sender);
        }


        /**
         * @param weekflag 传入的是周几
         * @param dateTime 传入的是时间戳（设置当天的年月日+从选择框拿来的时分秒）
         * @return 返回起始闹钟时间的时间戳
         */
        private long calMethod(int weekflag, long dateTime) {
            long time = 0;
            //weekflag == 0表示是按天为周期性的时间间隔或者是一次行的，weekfalg非0时表示每周几的闹钟并以周为时间间隔
            if (weekflag != 0) {
                Calendar c = Calendar.getInstance();
                int week = c.get(Calendar.DAY_OF_WEEK);
                if (1 == week) {
                    week = 7;
                } else if (2 == week) {
                    week = 1;
                } else if (3 == week) {
                    week = 2;
                } else if (4 == week) {
                    week = 3;
                } else if (5 == week) {
                    week = 4;
                } else if (6 == week) {
                    week = 5;
                } else if (7 == week) {
                    week = 6;
                }

                if (weekflag == week) {
                    if (dateTime > System.currentTimeMillis()) {
                        time = dateTime;
                    } else {
                        time = dateTime + 7 * 24 * 3600 * 1000;
                    }
                } else if (weekflag > week) {
                    time = dateTime + (weekflag - week) * 24 * 3600 * 1000;
                } else if (weekflag < week) {
                    time = dateTime + (weekflag - week + 7) * 24 * 3600 * 1000;
                }
            } else {
                if (dateTime > System.currentTimeMillis()) {
                    time = dateTime;
                } else {
                    time = dateTime + 24 * 3600 * 1000;
                }
            }
            return time;
        }

    }
}
