package com.tzl.android_collection.major_knowledge_point.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by tianzl on 2017/11/18.
 */

public class MyIntentService extends IntentService{
    private static final String TAG = "MyIntentService";

    public MyIntentService(){
        this("myIntentService");
    }

    /**
     * 构造函数
     * @param name
     */
    public MyIntentService(String name) {
        /**
         * 调用父类的构造函数
         * 构造函数参数=工作线程的名字
         */
        super("myIntentService");
    }
    /**
    * 复写onHandleIntent()方法
     * 实现耗时任务的操作
    */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //根据Intent的不同进行不同的事务处理
        String taskName = intent.getExtras().getString("taskName");
        switch (taskName) {
            case "task1":
                Log.i("myIntentService", "do task1");
                break;
            case "task2":
                Log.i("myIntentService", "do task2");
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
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
    }

}
