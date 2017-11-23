package com.tzl.android_collection.animator.recycler_effect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tzl.android_collection.R;
import com.tzl.android_collection.animator.recycler_effect.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by tianzl on 2017/11/23.
 */

public class EffectRvAdapter extends RecyclerView.Adapter<EffectRvAdapter.ViewHolder>  implements ItemTouchHelperAdapter {

    private Context context;
    private List<String> mData;
    private LayoutInflater inflater;
    public EffectRvAdapter(Context context,List<String> mData){
        this.context=context;
        this.mData=mData;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_effect_one,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        int fromPosition = source.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        if (fromPosition < mData.size() && toPosition < mData.size()) {
            //交换数据位置
            Collections.swap(mData, fromPosition, toPosition);
            //刷新位置交换
            notifyItemMoved(fromPosition, toPosition);
        }
        //移动过程中移除view的放大效果
        onItemClear(source);
    }

    @Override
    public void onItemDissmiss(RecyclerView.ViewHolder source) {
        int position = source.getAdapterPosition();
        mData.remove(position); //移除数据
        notifyItemRemoved(position);//刷新数据移除
    }

    @Override
    public void onItemSelect(RecyclerView.ViewHolder source) {
        //当拖拽选中时放大选中的view
        source.itemView.setScaleX(1.2f);
        source.itemView.setScaleY(1.2f);
    }

    @Override
    public void onItemClear(RecyclerView.ViewHolder source) {
        //拖拽结束后恢复view的状态
        source.itemView.setScaleX(1.0f);
        source.itemView.setScaleY(1.0f);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.item_effect_tv);
        }
    }
}
