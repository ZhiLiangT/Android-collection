package com.tzl.android_collection.multi_media;

import android.content.Intent;

import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.multi_media.ac.AudioPlayActivity;


public class MultiMediaActivity extends BaseActivity {
    private Button btAudio;
    private Button btVideo;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_multi_media);
    }

    @Override
    public void initView() {
        btAudio= (Button) findViewById(R.id.media_audio);
        btVideo= (Button) findViewById(R.id.media_video);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        btAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MultiMediaActivity.this, AudioPlayActivity.class);
                startActivity(intent);
            }
        });

    }
}
