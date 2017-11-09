package com.tzl.android_collection.external_lib;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.external_lib.rxjava.RxJavaTestActivity;

public class ExternalLibActivity extends BaseActivity implements View.OnClickListener{
    private Button btRxJava;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_external_lib);
    }

    @Override
    public void initView() {
        btRxJava= (Button) findViewById(R.id.lib_rxjava);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        btRxJava.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lib_rxjava:
                Intent intentRxJava=new Intent(this, RxJavaTestActivity.class);
                startActivity(intentRxJava);
                break;
        }
    }
}
