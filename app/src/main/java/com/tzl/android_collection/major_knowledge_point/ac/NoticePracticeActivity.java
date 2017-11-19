package com.tzl.android_collection.major_knowledge_point.ac;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

public class NoticePracticeActivity extends BaseActivity implements View.OnClickListener{
    private Button bt1,bt2,bt3,bt4;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_notice_practice);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.notice_bt1);
        bt2=(Button) findViewById(R.id.notice_bt2);
        bt3=(Button) findViewById(R.id.notice_bt3);
        bt4=(Button) findViewById(R.id.notice_bt4);
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
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.notice_bt1:
                test1();
                break;
            case R.id.notice_bt2:
                test2();
                break;
            case R.id.notice_bt3:
                seekbarTest();
                break;
            case R.id.notice_bt4:
                customLayout();
                break;
        }
    }
    //简单通知栏样式1
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void test1(){
        Notification builder=new Notification.Builder(this)
                //设置小图标
                .setSmallIcon(R.mipmap.emoji13)
                //设置左边大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                //设置内容
                .setContentText("我是一个简单的通知")
                //设置标题
                .setContentTitle("我是通知的标题")
                //点击进入的Activity
                .setContentIntent(PendingIntent.getActivity(this,1,new Intent(this,NoticePracticeActivity.class),PendingIntent.FLAG_CANCEL_CURRENT))
                .build();
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,builder);

    }
    //简单通知栏样式2
    public void test2(){

    }
    //显示进度通知栏
    public void seekbarTest(){

    }
    //自定义布局样式通知栏
    public void customLayout(){

    }
}
