package com.tzl.android_collection.multi_media.ac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.tzl.android_collection.R;

public class AudioPlayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play);
        recyclerView= (RecyclerView) findViewById(R.id.audio_rv);
        initData();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {

    }
}
