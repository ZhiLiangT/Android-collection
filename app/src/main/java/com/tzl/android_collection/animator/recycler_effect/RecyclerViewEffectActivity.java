package com.tzl.android_collection.animator.recycler_effect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.animator.recycler_effect.adapter.EffectRvAdapter;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewEffectActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private EffectRvAdapter adapter;
    private List<String> mData;
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_recycler_view_effect);
    }

    @Override
    public void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.effect_rv);
    }

    @Override
    public void initData() {
        mData=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData.addAll(getList());
        adapter=new EffectRvAdapter(this,mData);
        recyclerView.setAdapter(adapter);
        //创建SimpleItemTouchHelperCallback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void initEvent() {

    }
    public List<String> getList(){
        List<String> list=new ArrayList<>();
        char a='A';
        for (int i=0;i<20;i++){
            list.add(""+a);
            a++;
        }
        return list;
    }

}
