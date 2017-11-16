package com.tzl.android_collection.animator;

import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

public class AnimatorActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_animator);
    }

    @Override
    public void initView() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData() {
        ValueAnimator animator = ValueAnimator.ofArgb( 0xffE91E63, 0xff2196F3);
        animator.setDuration(10000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
        animator.start();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}
