package com.tzl.android_collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.tzl.android_collection.animator.AnimatorActivity;
import com.tzl.android_collection.common.CommonSkillActivity;
import com.tzl.android_collection.custom_view.CustomViewActivity;
import com.tzl.android_collection.database.DatabaseActivity;
import com.tzl.android_collection.external_lib.ExternalLibActivity;
import com.tzl.android_collection.external_sdk.ExternalSDKActivity;
import com.tzl.android_collection.main_utils.ClassifyBean;
import com.tzl.android_collection.main_utils.DataUtils;
import com.tzl.android_collection.main_utils.MainRvAdapter;
import com.tzl.android_collection.major_knowledge_point.MajoyKnowPointActivity;
import com.tzl.android_collection.multi_media.MultiMediaActivity;
import com.tzl.android_collection.new_features.NewFeaturesActivity;
import com.tzl.android_collection.photo_manage.PhotoManageActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainRvAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    public void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.main_recycler);
    }

    public void initData() {
        adapter=new MainRvAdapter(this, DataUtils.getClassifyList());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public void initEvent() {
        adapter.setOnItemClickListener(new MainRvAdapter.OnItemClickListener() {
            @Override
            public void onClick(ClassifyBean bean) {
                int type=bean.getType();
                Intent intent=new Intent();
                switch (type){
                    case 0:
                        intent.setClass(MainActivity.this, AnimatorActivity.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, CustomViewActivity.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this,DatabaseActivity.class);
                        break;
                    case 3:
                        intent.setClass(MainActivity.this, ExternalLibActivity.class);
                        break;
                    case 4:
                        intent.setClass(MainActivity.this, PhotoManageActivity.class);
                        break;
                    case 5:
                        intent.setClass(MainActivity.this, MultiMediaActivity.class);
                        break;
                    case 6:
                        intent.setClass(MainActivity.this, MajoyKnowPointActivity.class);
                        break;
                    case 7:
                        intent.setClass(MainActivity.this, ExternalSDKActivity.class);
                        break;
                    case 8:
                        intent.setClass(MainActivity.this, NewFeaturesActivity.class);
                        break;
                    case 9:
                        intent.setClass(MainActivity.this, CommonSkillActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
