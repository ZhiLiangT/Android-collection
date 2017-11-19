package com.tzl.android_collection.major_knowledge_point.service.ui.ac;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.major_knowledge_point.service.adapter.ServiceViewPagerAdapter;
import com.tzl.android_collection.major_knowledge_point.service.ui.fm.IntentServiceFragment;
import com.tzl.android_collection.major_knowledge_point.service.ui.fm.LocalServiceFragment;
import com.tzl.android_collection.major_knowledge_point.service.ui.fm.RemoteServiceFragment;
import com.tzl.android_collection.major_knowledge_point.service.ui.fm.StageServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class ServiceTestActivity extends BaseActivity implements View.OnClickListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String [] strings=new String[]{"本地Service","前台Service","远程Service","IntentService"};
    private List<Fragment> fragments;
    private ServiceViewPagerAdapter adapter;
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_service_test);
    }

    @Override
    public void initView() {
       tabLayout=(TabLayout) findViewById(R.id.service_tablayout);
       viewPager=(ViewPager) findViewById(R.id.service_viewPager);
    }

    @Override
    public void initData() {
        fragments=new ArrayList<>();
        fragments.add(new LocalServiceFragment());
        fragments.add(new StageServiceFragment());
        fragments.add(new RemoteServiceFragment());
        fragments.add(new IntentServiceFragment());
        for (int i=0;i<strings.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(strings[i]));
        }
        adapter=new ServiceViewPagerAdapter(getSupportFragmentManager(),this,strings,fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}
