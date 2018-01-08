package com.jci.classlock.common;

import android.app.Application;

import com.jci.classlock.features.screenlock.timetask.AlarmService;
import com.jci.classlock.greendao.gen.DaoMaster;
import com.jci.classlock.greendao.gen.DaoSession;
import com.orhanobut.logger.Logger;
import com.xdandroid.hellodaemon.DaemonEnv;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class GlobalApplication extends Application {

    public static final String TAG = "fire";
    private static GlobalApplication mClassLockApplication;
    private static int WAKE_TIME = 60 * 1000;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        if (mClassLockApplication == null){
            mClassLockApplication = this;
        }
        AppStatusTracker.init(this);
        Logger.init(TAG);
        JMessageClient.init(this);
        DaemonEnv.initialize(
                mClassLockApplication,
                AlarmService.class,
                WAKE_TIME);
    }


    public static synchronized GlobalApplication getInstance() {
        return mClassLockApplication;
    }
}
