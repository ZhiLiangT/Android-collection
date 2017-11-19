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

public class StageServiceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_service_stage,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
