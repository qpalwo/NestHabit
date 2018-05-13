package com.example.nesthabit.model;

import android.util.Log;

import com.example.nesthabit.model.bean.Clock;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by 肖宇轩 on 2018/4/3.
 */

public class DataUtil {

    /**
     * 返回unix时间戳
     *
     * @return
     */

    public static long getUnixStamp() {

        return System.currentTimeMillis() / 1000;

    }


    /**
     * 时间戳转化为时间格式
     *
     * @param timeStamp
     * @return
     */

    public static String timeStampToStr(long timeStamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date = sdf.format(timeStamp * 1000);

        return date;

    }

    /**
     * 得到日期   yyyy-MM-dd
     *
     * @param timeStamp  时间戳
     * @return
     */

    public static String formatDate(long timeStamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String date = sdf.format(timeStamp * 1000);

        return date;

    }

    /**
     * 得到时间  HH:mm:ss
     *
     * @param timeStamp   时间戳
     * @return
     */

    public static String getTime(long timeStamp) {

        String time = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String date = sdf.format(timeStamp * 1000);

        String[] split = date.split(" ");

        if (split.length > 1) {

            time = split[1];

        }

        return time;

    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */

    public static String convertTimeToFormat(long timeStamp) {

        long curTime = System.currentTimeMillis() / (long) 1000;

        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {

            return "刚刚";

        } else if (time >= 60 && time < 3600) {

            return time / 60 + "分钟前";

        } else if (time >= 3600 && time < 3600 * 24) {

            return time / 3600 + "小时前";

        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {

            return time / 3600 / 24 + "天前";

        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {

            return time / 3600 / 24 / 30 + "个月前";

        } else if (time >= 3600 * 24 * 30 * 12) {

            return time / 3600 / 24 / 30 / 12 + "年前";

        } else {

            return "刚刚";

        }

    }

    public static Clock getNextClock() {
        List<Clock> clockList = DataSupport.findAll(Clock.class);
        if (clockList.isEmpty()) {
            return null;
        }
        Clock nextClock = null;
        long nextClockTime = Long.MAX_VALUE;
        long flagTime = nextClockTime;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        for (Clock clock : clockList) {
            if (clock.getIsOpen() == 1) {
                long currentTime = getUnixStamp();
                int durationLevel = clock.getDurationLevel();
                int mask = 0x01;
                mask <<= dayOfWeek - 1;

                if ((durationLevel & mask) != 0
                        && clock.getTimeHour() >= hour
                        && clock.getTimeMin() > minute) {
                    flagTime = currentTime + (clock.getTimeHour() - hour) * 3600
                            + (clock.getTimeMin() - minute) * 60;
                } else {
                    currentTime += (23 - hour) * 3600 + (60 - minute) * 60;
                    for (int i = 0; i < 7; i++) {
                        mask <<= 1;
                        if (mask > 0x40) {
                            mask = 0x01;
                        }
                        if ((durationLevel & mask) != 0) {
                            flagTime = currentTime + clock.getTimeHour() * 3600
                                    + clock.getTimeMin() * 60;
                            break;
                        } else {
                            currentTime += 24 * 3600;
                        }
                    }
                }

                if (flagTime < nextClockTime) {
                    nextClockTime = flagTime;
                    nextClock = clock;
                }
            }
        }
        return nextClock;
    }

    public static long getNextClockTime(Clock clock) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        long flagTime = Long.MAX_VALUE;
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        long currentTime = getUnixStamp();
        int durationLevel = clock.getDurationLevel();
        int mask = 0x01;
        mask <<= dayOfWeek - 1;

        if ((durationLevel & mask) != 0
                && clock.getTimeHour() >= hour
                && clock.getTimeMin() > minute) {
            flagTime = currentTime + (clock.getTimeHour() - hour) * 3600
                    + (clock.getTimeMin() - minute) * 60;
        } else {
            currentTime += (23 - hour) * 3600 + (60 - minute) * 60;
            for (int i = 0; i < 7; i++) {
                mask <<= 1;
                if (mask > 0x40) {
                    mask = 0x01;
                }
                if ((durationLevel & mask) != 0) {
                    flagTime = currentTime + clock.getTimeHour() * 3600
                            + clock.getTimeMin() * 60;
                    break;
                } else {
                    currentTime += 24 * 3600;
                }
            }
        }
        return flagTime - second;
    }

    public static long getNextClockTime() {
        long nextClockTime = Long.MAX_VALUE;
        long flagTime = nextClockTime;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        List<Clock> clockList = DataSupport.findAll(Clock.class);
        for (Clock clock : clockList) {
            if (clock.getIsOpen() == 1) {
                long currentTime = getUnixStamp();
                int durationLevel = clock.getDurationLevel();
                int mask = 0x01;
                mask <<= dayOfWeek - 1;

                if ((durationLevel & mask) != 0
                        && clock.getTimeHour() >= hour
                        && clock.getTimeMin() > minute) {
                    flagTime = currentTime + (clock.getTimeHour() - hour) * 3600
                            + (clock.getTimeMin() - minute) * 60;
                } else {
                    currentTime += (23 - hour) * 3600 + (60 - minute) * 60;
                    for (int i = 0; i < 7; i++) {
                        mask <<= 1;
                        if (mask > 0x40) {
                            mask = 0x01;
                        }
                        if ((durationLevel & mask) != 0) {
                            flagTime = currentTime + clock.getTimeHour() * 3600
                                    + clock.getTimeMin() * 60;
                            break;
                        } else {
                            currentTime += 24 * 3600;
                        }
                    }
                }

                if (flagTime < nextClockTime) {
                    nextClockTime = flagTime;
                }
            }
        }
        return nextClockTime - second;
    }

}
