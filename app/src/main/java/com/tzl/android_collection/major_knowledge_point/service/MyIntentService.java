package com.tzl.android_collection.major_knowledge_point.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.tzl.android_collection.major_knowledge_point.service.ui.fm.IntentServiceFragment;

/**
 * Created by tianzl on 2017/11/18.
 */

public class MyIntentService extends IntentService{
    private static final String TAG = "MyIntentService";
    private boolean isRunning=true;
    private int count=0;
    private LocalBroadcastManager localBroadcastManager;

    public MyIntentService(){
        super("myIntentService");
    }

    /**
    * 复写onHandleIntent()方法
     * 实现耗时任务的操作
    */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            sendThreadStatus("线程启动",count);
            Thread.sleep(1_000);
            sendServiceStatus("服务运行中");
            isRunning=true;
            count=0;
            while (isRunning){
                count++;
                if (count>=100){
                    isRunning=false;
                }
                Thread.sleep(50);
                sendThreadStatus("线程运行中",count);
            }
            sendThreadStatus("线程运行结束",count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        sendServiceStatus("服务启动");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        sendServiceStatus("服务结束");
    }
    //发送服务状态信息
    public void sendServiceStatus(String status){
        Intent intent=new Intent(IntentServiceFragment.ACTION);
        intent.putExtra("status",status);
        localBroadcastManager.sendBroadcast(intent);
    }
    //发送线程状态信息
    public void sendThreadStatus(String status,int progress){
        Intent intent=new Intent(IntentServiceFragment.THREAD);
        intent.putExtra("status",status);
        intent.putExtra("progress",progress);
        localBroadcastManager.sendBroadcast(intent);
    }

}
