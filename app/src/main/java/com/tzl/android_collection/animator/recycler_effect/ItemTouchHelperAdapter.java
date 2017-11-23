package com.tzl.android_collection.animator.recycler_effect;

import android.support.v7.widget.RecyclerView;

/**
 * Created by tianzl on 2017/11/23.
 */

public interface ItemTouchHelperAdapter {
    //数据交换
    void onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target);

    //数据删除
    void onItemDissmiss(RecyclerView.ViewHolder source);

    //drag或者swipe选中
    void onItemSelect(RecyclerView.ViewHolder source);

    //状态清除
    void onItemClear(RecyclerView.ViewHolder source);
}
