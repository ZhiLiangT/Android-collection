package com.tzl.android_collection.external_lib.rxjava;

import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class FlowableTestActivity extends BaseActivity implements View.OnClickListener{
    private Button bt1;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_flowable_test);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.flow_1);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.flow_1:
                test1();
                break;
        }
    }

    private void test1() {
        Flowable<Integer> flowable=Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }, BackpressureStrategy.ERROR);


    }

}
