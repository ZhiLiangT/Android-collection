package com.tzl.android_collection.main_utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tianzl on 2017/11/7.
 */

public class MainRvAdapter extends RecyclerView.Adapter<MainRvAdapter.ViewHolder> {

    private Context context;
    private List<ClassifyBean> mData;

    public MainRvAdapter(Context context, List<ClassifyBean> mData){
        this.context=context;
        this.mData=mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
