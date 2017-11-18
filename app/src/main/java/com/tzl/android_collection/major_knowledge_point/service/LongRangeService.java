package com.tzl.android_collection.major_knowledge_point.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tzl.android_collection.major_knowledge_point.aidl.MyAIDLService;

/**
 * Created by tianzl on 2017/11/18.
 */

public class LongRangeService extends Service {

    private static final String TAG = "LongRangeService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: process id is"+ Process.myPid());
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return mBinder;
    }

    MyAIDLService.Stub mBinder=new MyAIDLService.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public String toUpper(String str) throws RemoteException {
            if (str!=null){
                return str.toUpperCase();
            }
            return null;
        }
    };

}
