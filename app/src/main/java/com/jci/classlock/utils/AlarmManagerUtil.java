package com.jci.classlock.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.jci.classlock.common.GlobalApplication;

/**
 * Created by Administrator on 2017/5/29 0029.
 */

public class AlarmManagerUtil {

    public static AlarmManager getAlarmManager(Context context){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        return am;
    }

    public static void setAlarmTime(long triggerAtTime, String action) {
        AlarmManager mAlarmManager = AlarmManagerUtil.getAlarmManager(GlobalApplication.getInstance());
        Intent intent = new Intent(action);
        PendingIntent pi = PendingIntent.getBroadcast(GlobalApplication.getInstance(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            mAlarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            mAlarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        }else{
            mAlarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        }
    }

    public static void cancelAlarm(String action){
        Intent intent = new Intent(action);
        PendingIntent pi = PendingIntent.getBroadcast(GlobalApplication.getInstance(), 0, intent, PendingIntent
                .FLAG_CANCEL_CURRENT);
        AlarmManager am = AlarmManagerUtil.getAlarmManager(GlobalApplication.getInstance());
        am.cancel(pi);
    }

}
