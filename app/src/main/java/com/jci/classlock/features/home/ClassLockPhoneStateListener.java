package com.jci.classlock.features.home;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * @version $Rev$
 * @auther 谭火朋
 * @time 2017/10/17 0017 23:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDEs ${TODO}
 */

public class ClassLockPhoneStateListener extends PhoneStateListener {
    @Override
    public void onCallStateChanged(int state, String incomingNumber)
    {
        switch (state)
        {
            //电话通话的状态
            case TelephonyManager.CALL_STATE_OFFHOOK:
                break;
            //电话响铃的状态
            case TelephonyManager.CALL_STATE_RINGING:
                break;
            //空闲中
            case TelephonyManager.CALL_STATE_IDLE:
                break;
        }
        super.onCallStateChanged(state, incomingNumber);
    }
}
