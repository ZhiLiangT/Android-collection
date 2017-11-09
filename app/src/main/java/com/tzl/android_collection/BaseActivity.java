package com.tzl.android_collection;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tianzl on 2017/11/8.
 */

public abstract class  BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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
