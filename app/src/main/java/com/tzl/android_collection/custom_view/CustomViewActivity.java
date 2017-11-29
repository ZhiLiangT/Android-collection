package com.tzl.android_collection.custom_view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.ac.CustomViewPagerTestActivity;
import com.tzl.android_collection.custom_view.ac.LoweTestActivity;
import com.tzl.android_collection.custom_view.ac.ProgressTestActivity;
import com.tzl.android_collection.custom_view.ac.SchedulingViewActivity;

/**
 * 自定义View
 */
public class CustomViewActivity extends BaseActivity implements View.OnClickListener{

    private Button bt1,bt2,bt3,bt4,bt5;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_custom_view);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.custom_bt1);
        bt1.setOnClickListener(this);
        bt2= (Button) findViewById(R.id.custom_bt2);
        bt2.setOnClickListener(this);
        bt3= (Button) findViewById(R.id.custom_bt3);
        bt3.setOnClickListener(this);
        bt4= (Button) findViewById(R.id.custom_bt4);
        bt4.setOnClickListener(this);
        bt5= (Button) findViewById(R.id.custom_bt5);
        bt5.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.custom_bt1:
                Intent intentLowe=new Intent(this, LoweTestActivity.class);
                startActivity(intentLowe);
                break;
            case R.id.custom_bt2:
                Intent intentPro=new Intent(this, ProgressTestActivity.class);
                startActivity(intentPro);
                break;
            case R.id.custom_bt3:
                Intent intentVP=new Intent(this, CustomViewPagerTestActivity.class);
                startActivity(intentVP);
                break;
            case R.id.custom_bt4:
                break;
            case R.id.custom_bt5:
                Intent intent5=new Intent(this, SchedulingViewActivity.class);
                startActivity(intent5);
                break;

        }
    }
}
