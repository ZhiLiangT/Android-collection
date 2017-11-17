package com.tzl.android_collection.common;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.common.ac.FileManagerTestActivity;

/**
 * 常用知识点
 */
public class CommonSkillActivity extends BaseActivity implements View.OnClickListener{

    private Button bt1,bt2,bt3;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_common_skill);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.common_bt1);
        bt2= (Button) findViewById(R.id.common_bt2);
        bt3= (Button) findViewById(R.id.common_bt3);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
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
        }
    }
}
