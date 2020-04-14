package com.example.xiaoclassmate;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends Activity{
    @Override
    // onCreate() Activity生命周期的開始，將狀態數據以 key-value 的形式放入到 saveInsanceState
    // 當一個 Activity 被創建時，就能從 onCreate 的參數 saveInsanceState 中獲得狀態數據
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 透過 setContentView 轉換 layout
        setContentView(R.layout.activity_welcome);
        message_Handler.sendEmptyMessageDelayed(goto_activity_main, 2500);
    }

    private static final int goto_activity_main = 0;
    private Handler message_Handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case goto_activity_main:
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };
}