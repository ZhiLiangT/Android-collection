package com.tzl.android_collection.multi_media.ac;

import android.support.v7.widget.RecyclerView;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

public class AudioPlayActivity extends BaseActivity {

    private RecyclerView recyclerView;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_audio_play);
    }

    @Override
    public void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.audio_rv);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

}
