package com.tzl.android_collection.custom_view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tzl.android_collection.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class DialogShiftAdapter extends RecyclerView.Adapter<DialogShiftAdapter.ViewHolder> {

    private List<String> mData;
    private Context context;
    private LayoutInflater inflater;
    private Map<Integer,Boolean> stateMap=new HashMap<>();
    int changePos=0;

    public DialogShiftAdapter(Context context, List<String> mData){
        this.context=context;
        this.mData=mData;
        inflater=LayoutInflater.from(context);
        initMap();
    }
    public void initMap(){
        for (int i=0;i<mData.size();i++){
            if (i==0){
                stateMap.put(i,true);
            }else {
                stateMap.put(i,false);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_dialog_shift,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvContent.setText(mData.get(position));
        if (stateMap.get(position)){
            holder.ivIcon.setSelected(true);
        }else {
            holder.ivIcon.setSelected(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(mData.get(position),position);
                changeMap(position);
            }
        });
    }
    public void changeMap(int pos){
        stateMap.put(pos,true);
        stateMap.put(changePos,false);
        changePos=pos;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener{
        void onItemClick(String msg, int pos);
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
        ImageView ivIcon;
        TextView tvContent;
        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon=itemView.findViewById(R.id.item_dialog_shift_img);
            tvContent=itemView.findViewById(R.id.item_dialog_shift_tv);
        }
    }
}
