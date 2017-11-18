package com.tzl.android_collection.major_knowledge_point.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by tianzl on 2017/11/18.
 */

public class TestService extends Service {

    private static final String TAG = "TestService";
    public MyBinder bind=new MyBinder();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return bind;
    }
    public class MyBinder extends Binder {

        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }

    }
}
