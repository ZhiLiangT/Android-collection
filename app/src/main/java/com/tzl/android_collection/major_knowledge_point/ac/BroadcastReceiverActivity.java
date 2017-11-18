package com.tzl.android_collection.major_knowledge_point.ac;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

public class BroadcastReceiverActivity extends BaseActivity implements View.OnClickListener{
    private Button bt1;
    private String imagepath;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_broadcast_receiver);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.bro_bt1);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bro_bt1:
                registerSDcard();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastRec);
    }

    public void registerSDcard(){
        // 在IntentFilter中选择你要监听的行为
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_MOUNTED);// sd卡被插入，且已经挂载
        intentFilter.setPriority(1000);// 设置最高优先级
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);// sd卡存在，但还没有挂载
        intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);// sd卡被移除
        intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);// sd卡作为 USB大容量存储被共享，挂载被解除
        intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);// sd卡已经从sd卡插槽拔出，但是挂载点还没解除
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);// 开始扫描
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);// 扫描完成
        intentFilter.addDataScheme("file");
        registerReceiver(broadcastRec, intentFilter);// 注册监听函数
    }
    private final BroadcastReceiver broadcastRec = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.MEDIA_MOUNTED"))// SD
            // 卡已经成功挂载
            {

                // 你的SD卡路径
                imagepath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();

            } else if (action.equals("android.intent.action.MEDIA_REMOVED")
                    || action.equals("android.intent.action.ACTION_MEDIA_UNMOUNTED")
                    || action.equals("android.intent.action.ACTION_MEDIA_BAD_REMOVAL")) {
                // 各种未挂载状态
                imagepath = android.os.Environment.getDataDirectory().getAbsolutePath();// 你的本地路径

            }else if (action.equals(Intent.ACTION_MEDIA_SCANNER_STARTED)){
                //开始扫描

            }else if (action.equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)){
                //扫描完成
            }else if (action.equals(Intent.ACTION_MEDIA_SHARED)){
                //扩展介质的挂载被解除 (unmount)。因为它已经作为 USB 大容量存储被共享
            }else {
                //其他状态...
            }
            Log.i("MainActivity", "imagepath---" + imagepath);
        }
    };
}
