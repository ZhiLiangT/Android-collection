package com.tzl.android_collection.major_knowledge_point.service.ui.fm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tzl.android_collection.R;

/**
 * Created by tianzl on 2017/11/19.
 */

public class IntentServiceFragment extends Fragment {
    public static final String ACTION="com.service.MyIntentService";
    public static final String THREAD="com.service.MyThread";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_service_intent,container,false);
        return view;
    }
}
