package com.jci.classlock.features.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.jci.classlock.R;
import com.jci.classlock.common.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jump();
            }
        },2000);
    }

    private void jump() {
        start(this, LoginActivity.class);
    }

    private void start(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
    }


}
