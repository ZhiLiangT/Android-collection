package com.tzl.android_collection.major_knowledge_point.ac;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.major_knowledge_point.aidl.MyAIDLService;
import com.tzl.android_collection.major_knowledge_point.service.LongRangeService;
import com.tzl.android_collection.major_knowledge_point.service.ReceptionService;
import com.tzl.android_collection.major_knowledge_point.service.TestService;

public class ServiceTestActivity extends BaseActivity implements View.OnClickListener{

    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10;
    private static final String TAG = "ServiceTestActivity";
    private TestService.MyBinder myBinder;
    private MyAIDLService myAIDLService;
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_service_test);

    }
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder= (TestService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private ServiceConnection longconnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDLService=MyAIDLService.Stub.asInterface(service);
            try {
                int result=myAIDLService.plus(3,5);
                String upper=myAIDLService.toUpper("hello world");
                Log.i(TAG, "result: "+result);
                Log.i(TAG, "upper: "+upper);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void initView() {
        bt1=(Button) findViewById(R.id.service_bt1);
        bt2=(Button) findViewById(R.id.service_bt2);
        bt3=(Button) findViewById(R.id.service_bt3);
        bt4=(Button) findViewById(R.id.service_bt4);
        bt5=(Button) findViewById(R.id.service_bt5);
        bt6=(Button) findViewById(R.id.service_bt6);
        bt7=(Button) findViewById(R.id.service_bt7);
        bt8=(Button) findViewById(R.id.service_bt8);
        bt9=(Button) findViewById(R.id.service_bt9);
        bt10=(Button) findViewById(R.id.service_bt10);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_bt1:
                Intent intentStartService=new Intent(this, TestService.class);
                startService(intentStartService);
                break;
            case R.id.service_bt2:
                Intent intentStopService=new Intent(this, TestService.class);
                stopService(intentStopService);
                break;
            case R.id.service_bt3:
                Intent intentBindService=new Intent(this, TestService.class);
                bindService(intentBindService,connection,BIND_AUTO_CREATE);
                break;
            case R.id.service_bt4:
                unbindService(connection);
                break;
            case R.id.service_bt5:
                Intent intentService=new Intent(this, ReceptionService.class);
                startService(intentService);
                break;
            case R.id.service_bt6:
                Intent stopService=new Intent(this, ReceptionService.class);
                stopService(stopService);
                break;
            case R.id.service_bt7:
                Intent intentLongService=new Intent(this, LongRangeService.class);
                startService(intentLongService);
                break;
            case R.id.service_bt8:
                Intent intentStopLongService=new Intent(this, LongRangeService.class);
                stopService(intentStopLongService);
                break;
            case R.id.service_bt9:
                Intent bingLongService=new Intent(this,LongRangeService.class);
                bindService(bingLongService,longconnection,BIND_AUTO_CREATE);
                break;
            case R.id.service_bt10:
                unbindService(longconnection);
                break;
        }
    }
}
