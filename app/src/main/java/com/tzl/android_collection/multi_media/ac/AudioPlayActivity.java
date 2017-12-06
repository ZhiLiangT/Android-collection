package com.tzl.android_collection.multi_media.ac;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.multi_media.adapter.AudioAdapter;
import com.tzl.android_collection.multi_media.bean.AudioBean;
import com.tzl.android_collection.multi_media.utils.AudioUtils;

import java.util.ArrayList;
import java.util.List;

public class AudioPlayActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<AudioBean> mData;
    private AudioAdapter audioAdapter;
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
        mData=new ArrayList<>();
        audioAdapter=new AudioAdapter(this,mData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(audioAdapter);
        new AudioTask().execute();
    }
    class  AudioTask extends AsyncTask<Void,Integer,List<AudioBean>>{

        @Override
        protected List<AudioBean> doInBackground(Void... voids) {
            return AudioUtils.getAllAudio(AudioPlayActivity.this);
        }

        @Override
        protected void onPostExecute(List<AudioBean> audioBeans) {
            super.onPostExecute(audioBeans);
            mData.clear();
            mData.addAll(audioBeans);
            audioAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initEvent() {
        audioAdapter.setOnItemClickListener(new AudioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AudioBean audioBean) {
                Intent intent=new Intent(AudioPlayActivity.this,AudioPlayDetailsActivity.class);
                intent.putExtra("AUDIO",audioBean);
                startActivity(intent);

            }
        });
    }

}
