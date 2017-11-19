package com.tzl.android_collection.major_knowledge_point.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.tzl.android_collection.R;
import com.tzl.android_collection.major_knowledge_point.service.ui.ac.ServiceTestActivity;

public class ReceptionService extends Service {

    private static final String TAG = "ReceptionService";

    public ReceptionService() {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        Notification notification1=new Notification.Builder(this)
                /**设置通知左边的大图标**/
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                /**设置通知右边的小图标**/
                .setSmallIcon(R.mipmap.ic_launcher)
                /**通知首次出现在通知栏，带上升动画效果的**/
                .setTicker("通知来了")
                /**设置通知的标题**/
                .setContentTitle("我在下载东西")
                /**设置通知的内容**/
                .setContentText("我是被下载的东西")
                .setContentIntent(PendingIntent.getActivity(this,1,new Intent(this, ServiceTestActivity.class),PendingIntent.FLAG_CANCEL_CURRENT))
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        /**发起通知**/
        notificationManager.notify(0, notification1);

        startForeground(1, notification1);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return null;
    }
}
