package com.jci.classlock.features.screenlock.timetask;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xdandroid.hellodaemon.AbsWorkService;

public class AlarmService extends AbsWorkService {


    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        return null;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {
    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {

    }

    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        return null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent, Void alwaysNull) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {
    }


}
