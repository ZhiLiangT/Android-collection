package com.tzl.android_collection.major_knowledge_point.service.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017-03-15.
 */

public class ServiceViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] arrayTitle;
    private List<Fragment> listFragment;
    private Context context;

    public ServiceViewPagerAdapter(FragmentManager fm, Context context, String[] arrayTitle, List<Fragment> listFragment) {
        super(fm);
        this.context=context;
        this.arrayTitle=arrayTitle;
        this.listFragment=listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayTitle.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle[position%arrayTitle.length];
    }
}
