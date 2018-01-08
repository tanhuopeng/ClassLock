package com.jci.classlock.features.screenlock.screen;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jci.classlock.R;
import com.jci.classlock.common.GlobalApplication;
import com.jci.classlock.utils.ScreenUtils;

import java.util.Random;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

import static android.content.Context.WINDOW_SERVICE;

/**
 * @version $Rev$
 * @auther 谭火朋
 * @time 2017/10/8 0008 0:16
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDEs ${TODO}
 */

public class RootView extends LinearLayout {

    private WindowManager wm;
    private ViewGroup mViewContent;
    private Button mBtn_Unlock;
    private Button mBtn_Urgency;
    private int[] mLockScreenBackground = new int[]{
            R.mipmap.screen_lock_background_night_1, R.mipmap.screen_lock_background_night_2,
            R.mipmap.screen_lock_background_night_3, R.mipmap.screen_lock_background_night_4,
            R.mipmap.screen_lock_background_night_5, R.mipmap.screen_lock_background_night_6,
            R.mipmap.screen_lock_background_night_7, R.mipmap.screen_lock_background_night_8,
            R.mipmap.screen_lock_background_night_9, R.mipmap.screen_lock_background_night_10
    };

    public RootView(Context context) {
        super(context);
        initRootView();
    }

    public RootView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initRootView();
    }

    public RootView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRootView();
    }

    private void initRootView() {
        initWidget();
        initEvent();
    }

    private  void initEvent() {
        mBtn_Unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建跨应用会话
                Conversation con = Conversation.createSingleConversation("Test", "fafe7c4b72077c9b570fb026");
                MessageContent content = new TextContent("1");
//创建一条消息
                Message message = con.createSendMessage(content);
//发送消息
                JMessageClient.sendMessage(message);
                // TODO: 2017/10/5 0005 取消本次闹钟
            }
        });
        mBtn_Urgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建跨应用会话
                Conversation con = Conversation.createSingleConversation("Test", "fafe7c4b72077c9b570fb026");
                MessageContent content = new TextContent("2");
//创建一条消息
                Message message = con.createSendMessage(content);
//发送消息
                JMessageClient.sendMessage(message);
                removeLockView();
                // TODO: 2017/10/5 0005 取消本次闹钟
            }
        });
    }

    private void initWidget() {
        wm = (WindowManager) GlobalApplication.getInstance().getSystemService(WINDOW_SERVICE);
        mViewContent = (ViewGroup) View.inflate(GlobalApplication.getInstance(), R.layout.activity_screen_dialog, null);
        mViewContent.setBackgroundResource(mLockScreenBackground[new Random().nextInt(mLockScreenBackground.length)]);
        mBtn_Unlock = (Button) mViewContent.findViewById(R.id.mlockscreenDialogactivity_btn_unlock);
        mBtn_Urgency = (Button) mViewContent.findViewById(R.id.mlockscreenDialogactivity_btn_urgency);
    }

    //添加锁屏悬浮窗
    public void addLockView(){
        initRootView();
        wm.addView(mViewContent, genetateLayoutParams());
    }

    @NonNull
    private  WindowManager.LayoutParams genetateLayoutParams() {
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        // TYPE_SYSTEM_ERROR 系统内部错误提示，显示于所有内容之上。
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        //PixelFormat.OPAQUE像素格式不透明
        wmParams.format = PixelFormat.OPAQUE;
        //Flag，用于控制Window的显示
        wmParams.flags = WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
        wmParams.width = ScreenUtils.getScreenWidth();
        wmParams.height = ScreenUtils.getScreenHeight();
        return wmParams;
    }

    //移除锁屏悬浮窗
    public void removeLockView(){
        if(wm == null) return ;
        if (mViewContent == null)return ;
        wm.removeView(mViewContent);
    }

}
