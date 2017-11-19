package com.tzl.android_collection.major_knowledge_point;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.major_knowledge_point.ac.BroadcastReceiverActivity;
import com.tzl.android_collection.major_knowledge_point.ac.NoticePracticeActivity;
import com.tzl.android_collection.major_knowledge_point.ac.ServiceTestActivity;

public class MajoyKnowPointActivity extends BaseActivity implements View.OnClickListener{
    private Button bt1,bt2,bt3,bt4,bt5;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_majoy_know_point);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.major_bt1);
        bt2= (Button) findViewById(R.id.major_bt2);
        bt3= (Button) findViewById(R.id.major_bt3);
        bt4= (Button) findViewById(R.id.major_bt4);
        bt5= (Button) findViewById(R.id.major_bt5);

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.major_bt1:
                Intent intentReceiver=new Intent(this, BroadcastReceiverActivity.class);
                startActivity(intentReceiver);
                break;
            case R.id.major_bt2:
                Intent intentService=new Intent(this, ServiceTestActivity.class);
                startActivity(intentService);
                break;
            case R.id.major_bt3:
                break;
            case R.id.major_bt4:
                break;
            case R.id.major_bt5:
                Intent intentNotice=new Intent(this, NoticePracticeActivity.class);
                startActivity(intentNotice);
                break;
        }
    }
}
