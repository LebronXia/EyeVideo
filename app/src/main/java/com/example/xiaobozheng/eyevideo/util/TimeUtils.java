package com.example.xiaobozheng.eyevideo.util;

/**
 * Created by xiaobozheng on 11/29/2016.
 */

public class TimeUtils {
    public static String secToTime(int time){
        String timeStr = null;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00′00″";
        else {
            minute = time / 60;
            if (minute < 60){
                second = time % 60;
                timeStr = unitFormat(minute) + "′" + unitFormat(second);
            } else {
                minute = minute % 60;
                second = time - minute * 60;
                timeStr = unitFormat(minute) + "′" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
}
