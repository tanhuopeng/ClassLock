package com.jci.classlock.logger;

import com.jci.classlock.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * 创建人: 谭火朋
 * 创建时间: 2017/9/21 0021 16:55
 */

public class L {

    private static final boolean DEBUG = BuildConfig.DEBUG_MODE;

    public static void i(String msg) {
        if (DEBUG)
            Logger.i(msg);
    }

    public static void e(String msg) {
        if (DEBUG)
            Logger.e(msg);
    }

    public static void d(String msg) {
        if (DEBUG)
            Logger.d(msg);
    }

    public static void v(String msg) {
        if (DEBUG)
            Logger.v(msg);
    }

    public static void w(String msg) {
        if (DEBUG)
            Logger.w(msg);
    }


}
