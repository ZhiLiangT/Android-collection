package com.tzl.android_collection.main_utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tzl.android_collection.R;

import java.util.List;

/**
 * Created by tianzl on 2017/11/7.
 */

public class MainRvAdapter extends RecyclerView.Adapter<MainRvAdapter.ViewHolder> {

    private Context context;
    private List<ClassifyBean> mData;
    private LayoutInflater inflater;

    public MainRvAdapter(Context context, List<ClassifyBean> mData){
        this.context=context;
        this.mData=mData;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_main,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvLabel.setText(mData.get(position).getName());
        holder.ivIcon.setImageResource(mData.get(position).getRedId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(mData.get(position));
            }
        });
    }
    public interface OnItemClickListener{
        void onClick(ClassifyBean bean);
    }
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener lisktener){
        this.listener=lisktener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIcon;
        private TextView tvLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon=itemView.findViewById(R.id.item_main_icon);
            tvLabel=itemView.findViewById(R.id.item_main_label);
        }
    }
}
