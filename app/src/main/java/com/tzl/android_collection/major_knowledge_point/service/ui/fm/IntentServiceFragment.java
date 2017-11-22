package com.tzl.android_collection.major_knowledge_point.service.ui.fm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tzl.android_collection.R;
import com.tzl.android_collection.major_knowledge_point.service.MyIntentService;

/**
 * Created by tianzl on 2017/11/19.
 */

public class IntentServiceFragment extends Fragment {

    private Button btStart;
    private TextView tvSer;
    private TextView tvThread;
    private SeekBar seekBar;

    public static final String ACTION="com.service.MyIntentService";
    public static final String THREAD="com.service.MyThread";
    private MyBroadcastReceiver receiver;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_service_intent,container,false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initData() {
        mLocalBroadcastManager= LocalBroadcastManager.getInstance(getActivity());
        receiver=new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        intentFilter.addAction(THREAD);
        mLocalBroadcastManager.registerReceiver(receiver,intentFilter);
        tvSer.setText("服务状态：未运行");
        tvThread.setText("线程状态：未运行");
        seekBar.setMax(100);
        seekBar.setProgress(0);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case ACTION:
                    tvSer.setText("服务状态:"+intent.getStringExtra("status"));
                    break;
                case THREAD:
                    int progress = intent.getIntExtra("progress", 0);
                    tvThread.setText("线程状态:"+intent.getStringExtra("status"));
                    seekBar.setProgress(progress);
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(receiver);
    }

    private void initView(View view) {
        btStart=view.findViewById(R.id.fm_service_intent_bt_start);
        tvSer=view.findViewById(R.id.fm_service_intent_tv_ser);
        tvThread=view.findViewById(R.id.fm_service_intent_tv_thread);
        seekBar=view.findViewById(R.id.fm_service_intent_seekbar);
    }

    private void initEvent() {
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMService=new Intent(getActivity(), MyIntentService.class);
                getActivity().startService(intentMService);
            }
        });
    }
}
