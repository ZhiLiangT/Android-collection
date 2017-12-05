package com.tzl.android_collection.multi_media.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tzl.android_collection.R;
import com.tzl.android_collection.multi_media.bean.AudioBean;

import java.util.List;

/**
 * Created by tzl on 2017/12/3.
 */

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    public List<AudioBean> mData;
    private Context context;
    public LayoutInflater inflater;
    public AudioAdapter(Context context,List<AudioBean> mData){
        this.context=context;
        this.mData=mData;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_multi_media_audio,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvName.setText(mData.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(mData.get(position));
            }
        });
    }
    public interface OnItemClickListener{
        void onItemClick(AudioBean audioBean);
    }
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.item_multi_media_audio_name);
        }
    }
}
