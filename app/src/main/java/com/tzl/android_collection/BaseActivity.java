package com.tzl.android_collection;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by tianzl on 2017/11/8.
 */

public abstract class  BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        initView();
        initData();
        initEvent();
    }
    public abstract void initContentView();
    public abstract void initView();
    public abstract void initData();
    public abstract void initEvent();
}
