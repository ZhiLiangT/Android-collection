package com.tzl.android_collection.external_lib;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.external_lib.retrofit2.Retrofit2TestActivity;
import com.tzl.android_collection.external_lib.rxjava.RxJavaTestActivity;

public class ExternalLibActivity extends BaseActivity implements View.OnClickListener{
    private Button btRxJava;
    private Button btRetrofit2;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_external_lib);
    }

    @Override
    public void initView() {
        btRxJava= (Button) findViewById(R.id.lib_rxjava);
        btRetrofit2= (Button) findViewById(R.id.lib_retrofit2);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        btRxJava.setOnClickListener(this);
        btRetrofit2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lib_rxjava:
                Intent intentRxJava=new Intent(this, RxJavaTestActivity.class);
                startActivity(intentRxJava);
                break;
            case R.id.lib_retrofit2:
                Intent intentRetrofit2=new Intent(this, Retrofit2TestActivity.class);
                startActivity(intentRetrofit2);
                break;
        }
    }
}
