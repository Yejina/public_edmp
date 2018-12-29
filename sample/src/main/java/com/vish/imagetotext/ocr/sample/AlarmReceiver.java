package com.vish.imagetotext.ocr.sample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by SooBin on 2017-05-26.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "유통기한 알림!", Toast.LENGTH_LONG).show();

        NotificationManager notifier = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent2 = new Intent(context, saveActivity.class);
        PendingIntent pender = PendingIntent
                .getActivity(context, 0, intent2, 0);
        Notification.Builder builder = new Notification.Builder(context)
                .setContentIntent(pender)
                .setSmallIcon(android.R.drawable.btn_star_big_on)
                .setContentTitle("유통기한 알림")
                .setContentText("장바구니를 확인하세요")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setTicker("유통기한 알림");
        Notification notification = builder.build();

        notifier.notify(1234, notification);



    }
}
   /* String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;
    final String TAG = "BOOT_START_SERVICE";

    @Override
    public void onReceive(Context context, Intent intent) {//알람 시간이 되었을때 onReceive를 호출함

        NotificationManager nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
//NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고

        Intent intentActivity = new Intent(context, saveActivity.class); //그메세지를 클릭했을때 불러올엑티비티를 설정함
        intentActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);//플레그부분은 옵션인데 나도 자세하게 몰르겠음
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentActivity, PendingIntent.FLAG_UPDATE_CURRENT);
        String ticker = "ticker";//여긴 알림바에 등록될 글이랑 타이틀 적는곳.
        String title = "title";
        String text = "알림";
        // Create Notification Object
        Notification notification = new Notification
                (android.R.drawable.ic_input_add, ticker, System.currentTimeMillis());//알림바에 넣을 이미지 아이콘

        Notification.Builder builder = new Notification.Builder(context)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(text)
                *//*.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)*//*
                *//*.setAutoCancel(true)*//*
                *//*.setWhen(System.currentTimeMillis())*//*
                .setTicker("ticker");

        nm.notify(1, builder.build());//노티피에 1주는건 왜지??? 그것도 모르겠음.

    }*/



