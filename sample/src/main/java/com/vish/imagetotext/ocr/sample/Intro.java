package com.vish.imagetotext.ocr.sample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.croppersample.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YEJINA1202 on 2017-04-11.
 */

public class Intro extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);
// 알림 관리자를 구해서 멤버변수에 저장
    }

    public void onRegisterClick(View view) {
        Intent intent = new Intent(getApplicationContext(), main.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onCheckClick(View view) {
        Intent intent = new Intent(getApplicationContext(), checkList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onShoppingListClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ShoppingActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onFinishClick(View view) {
        moveTaskToBack(true);
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
