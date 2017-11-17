package com.tzl.android_collection.animator.ac;

import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

public class ColorGradientActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv1;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_color_gradient);
    }

    @Override
    public void initView() {
        tv1= (TextView) findViewById(R.id.anim_color_tv1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData() {
        tvFradient1();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void tvFradient1(){
        ValueAnimator animator = ValueAnimator.ofArgb( 0xffE91E63, 0xff2196F3);
        animator.setDuration(10000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv1.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        animator.start();
    }
}
