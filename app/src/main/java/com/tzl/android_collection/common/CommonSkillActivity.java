package com.tzl.android_collection.common;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.common.ac.FileManagerTestActivity;
import com.tzl.android_collection.common.ac.ManyTaskDownActivity;
import com.tzl.android_collection.common.ac.SpanTestActivity;

/**
 * 常用知识点
 */
public class CommonSkillActivity extends BaseActivity implements View.OnClickListener{

    private Button bt1,bt2,bt3,bt4,bt5,bt6;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_common_skill);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.common_bt1);
        bt2= (Button) findViewById(R.id.common_bt2);
        bt3= (Button) findViewById(R.id.common_bt3);
        bt4= (Button) findViewById(R.id.common_bt4);
        bt5= (Button) findViewById(R.id.common_bt5);
        bt6= (Button) findViewById(R.id.common_bt6);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_bt1:
                Intent intentFile=new Intent(this, FileManagerTestActivity.class);
                startActivity(intentFile);
                break;
            case R.id.common_bt2:
                break;
            case R.id.common_bt3:
                break;
            case R.id.common_bt4:
                Intent intentDown=new Intent(this, ManyTaskDownActivity.class);
                startActivity(intentDown);
                break;
            case R.id.common_bt5:
                startActivity(new Intent(this, SpanTestActivity.class));
                break;
            case R.id.common_bt6:
                startCountDown();
                break;
        }
    }

    /**
     * 开始倒计时
     */
    public void startCountDown(){
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                bt6.setText("当前倒数时间"+(int)millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                bt6.setText("倒计时结束");
            }
        }.start();
    }
}
