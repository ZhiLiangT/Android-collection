package com.tzl.android_collection.custom_view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tzl.android_collection.R;
import com.tzl.android_collection.main_utils.DisplayUtils;
import com.tzl.android_collection.main_utils.ViewUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class TestRVAdapter extends RecyclerView.Adapter<TestRVAdapter.ViewHolder> {

    private List<String> mData;
    private Context context;
    private LayoutInflater inflater;
    public TestRVAdapter(Context context, List<String> mData){
        this.context=context;
        this.mData=mData;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_test_rv,parent,false);
        return new ViewHolder(view);
    }
    public void settHeight(View view,int height){
        int h= DisplayUtils.dpTopx(context,height);
        ViewUtils.setViewWidthOrHeight(view, LinearLayoutCompat.LayoutParams.WRAP_CONTENT,h);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position<5){
            settHeight(holder.llRoot,30);
            holder.textView.setBackgroundColor(Color.parseColor("#ffd966"));
        }else {
            settHeight(holder.llRoot,40);
            holder.textView.setBackgroundColor(Color.WHITE);
        }
        holder.textView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout llRoot;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.item_test_tv);
            llRoot=itemView.findViewById(R.id.item_test_root);
        }
    }
}
