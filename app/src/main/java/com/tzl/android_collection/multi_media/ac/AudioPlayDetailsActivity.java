package com.tzl.android_collection.multi_media.ac;


import android.content.Intent;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.multi_media.bean.AudioBean;

public class AudioPlayDetailsActivity extends BaseActivity {

    private AudioBean audioBean;
    private String playPath;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_audio_play_details);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        audioBean=intent.getParcelableExtra("AUDIO");
        playPath=audioBean.getFileUrl();
    }

    @Override
    public void initEvent() {

    }
}
