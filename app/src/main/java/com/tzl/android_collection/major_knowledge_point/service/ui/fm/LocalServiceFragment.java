package com.tzl.android_collection.major_knowledge_point.service.ui.fm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.tzl.android_collection.R;
import com.tzl.android_collection.major_knowledge_point.service.LocalService;


/**
 * 本地服务
 * Created by tianzl on 2017/11/19.
 */

public class LocalServiceFragment extends Fragment implements View.OnClickListener{
    private Button btStart,btStop,btBind,btUnBind;
    private LocalService.MyBinder myBinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_service_local,container,false);
        init(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        btStart.setOnClickListener(this);
        btStop.setOnClickListener(this);
        btBind.setOnClickListener(this);
        btUnBind.setOnClickListener(this);
    }

    private void init(View view) {
        btStart=view.findViewById(R.id.fm_service_local_bt_start);
        btStop=view.findViewById(R.id.fm_service_local_bt_stop);
        btBind=view.findViewById(R.id.fm_service_local_bt_bind);
        btUnBind=view.findViewById(R.id.fm_service_local_bt_unbind);
    }
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder= (LocalService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fm_service_local_bt_start:
                Toast.makeText(getActivity(), "启动服务", Toast.LENGTH_SHORT).show();
                Intent intentStartService=new Intent(getActivity(), LocalService.class);
                getActivity().startService(intentStartService);
                break;
            case R.id.fm_service_local_bt_stop:
                Intent intentStopService=new Intent(getActivity(), LocalService.class);
                getActivity().stopService(intentStopService);
                break;
            case R.id.fm_service_local_bt_bind:
                Intent intentBindService=new Intent(getActivity(), LocalService.class);
                getActivity().bindService(intentBindService,connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.fm_service_local_bt_unbind:
                getActivity().unbindService(connection);
                break;

        }
    }
}
