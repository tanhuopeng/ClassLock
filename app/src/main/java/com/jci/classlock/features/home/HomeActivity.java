package com.jci.classlock.features.home;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jci.classlock.R;
import com.jci.classlock.common.BaseActivity;
import com.jci.classlock.features.courselist.CourseListActivity;
import com.jci.classlock.features.courselist.SelectTermActivity;
import com.jci.classlock.features.screenlock.screen.RootView;
import com.jci.classlock.utils.SPUtils;
import com.jci.classlock.utils.ToastUtils;
import com.jci.classlock.utils.WindowUtil;
import com.xdandroid.hellodaemon.IntentWrapper;

import cn.bmob.v3.Bmob;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

import static com.jci.classlock.R.id.action_back_app;
import static com.jci.classlock.R.id.action_lay_in_course;
import static com.jci.classlock.R.id.toolbar;

public class HomeActivity extends BaseActivity {

    private static final int REQUEST_MANAGE_OVERLAY_CODE = 1001;
    private Button mBtn_GotoLock;
    private Button mBtn_CourseList;
    private Button mBtn_JoinClass;
    private RootView mRootView;
    private Toolbar mToolbar;
    private LayoutInflater inflater;
    private EditText mEdt_InputClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bmob.initialize(this, "17fa46d3a72e3bcad648403999064c8f");
        requestPermission();
        initWidget();
        initEvent();
        JMessageClient.registerEventReceiver(this);
    }

    private void initEvent() {
        mBtn_GotoLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mRootView.addLockView();
//                    GlobalApplication.getInstance().startService(new Intent(GlobalApplication.getInstance(), AlarmService.class));
            }
        });
        mBtn_CourseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SPUtils.getInstance().getBoolean("haveCourse",false)){
                    Intent intent = new Intent(HomeActivity.this, CourseListActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtils.showShort("请先导入课表");
                }

            }
        });

        mBtn_JoinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass();
            }
        });
    }

    private void initWidget() {
        mBtn_GotoLock = (Button) this.findViewById(R.id.mhomeactivity_btn_unlock);
        mBtn_CourseList = (Button) this.findViewById(R.id.mhomeactivity_btn_course_list);
        mBtn_JoinClass = (Button) this.findViewById(R.id.mhomeactivity_btn_join_class);
        mRootView = new RootView(this);
        mToolbar = (Toolbar) this.findViewById(toolbar);
        mToolbar.inflateMenu(R.menu.menu_nearby);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case action_lay_in_course:
                        if (SPUtils.getInstance().getBoolean("isAddClass",false)){
                            Intent intent = new Intent(HomeActivity.this, SelectTermActivity.class);
                            startActivity(intent);
                        }else {
                            ToastUtils.showShort("请先加入班级");
                        }
                        break;
                    case action_back_app:
                        finish();
                        break;
                }
                return true;
            }
        });
    }

    //请求悬浮窗权限
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(HomeActivity.this)) {
                if (WindowUtil.isMIUIRom()) {
                    WindowUtil.openMiuiPermissionActivity(HomeActivity.this);
                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, REQUEST_MANAGE_OVERLAY_CODE);
                }
            }
        } else {
            if (WindowUtil.isMIUIRom()) {
                if (WindowUtil.isMiuiFloatWindowOpAllowed(HomeActivity.this)) {
                } else {
                    WindowUtil.openMiuiPermissionActivity(HomeActivity.this);
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_MANAGE_OVERLAY_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    ToastUtils.showShort("权限授予失败，无法开启悬浮窗");
                } else {
                    ToastUtils.showShort("权限授予成功");
                }
            }
        }
    }

    private void addClass() {
        View dialog;
        inflater = LayoutInflater.from(this);
        dialog = inflater.inflate(R.layout.dialog_create_class,null);
        mEdt_InputClass = (EditText) dialog.findViewById(R.id.mEdt_InputClass);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("加入班级");
        builder.setView(dialog);
        builder.setCancelable(true);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = mEdt_InputClass.getText().toString().trim();
                if (!TextUtils.isEmpty(input)){
                    //创建跨应用会话
                    Conversation con = Conversation.createSingleConversation("Test", "fafe7c4b72077c9b570fb026");
                    MessageContent content = new TextContent(input);
//创建一条消息
                    Message message = con.createSendMessage(content);
//发送消息
                    JMessageClient.sendMessage(message);
                    ToastUtils.showShort("请求已发出");
                    SPUtils.getInstance().put("classes", input);
                    SPUtils.getInstance().put("isAddClass",true);
                }else {
                    ToastUtils.showShort("班级不能为空");
                }
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        IntentWrapper.onBackPressed(this);
    }

    public void onEvent(MessageEvent event){
        Message msg = event.getMessage();

        switch (msg.getContentType()){
            case text:
                //处理文字消息
                TextContent textContent = (TextContent) msg.getContent();
                if (textContent.getText().toString().equals("3")){
                    mRootView.removeLockView();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
        JMessageClient.logout();
    }
}
