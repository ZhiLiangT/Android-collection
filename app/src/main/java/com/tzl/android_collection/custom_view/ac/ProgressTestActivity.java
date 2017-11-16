package com.tzl.android_collection.custom_view.ac;

import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.view.Sample08ObjectAnimatorView;
import com.tzl.android_collection.custom_view.view.SportsLinearView;
import com.tzl.android_collection.custom_view.view.SportsView;

public class ProgressTestActivity extends BaseActivity implements View.OnClickListener {
    private Sample08ObjectAnimatorView view1;
    private SportsView view2;
    private SportsLinearView view3;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_progress_test);
    }

    @Override
    public void initView() {
        view1= (Sample08ObjectAnimatorView) findViewById(R.id.progress_1);
        view2= (SportsView) findViewById(R.id.progress_2);
        view3= (SportsLinearView) findViewById(R.id.progress_3);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData() {
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 100f);
        anim.setDuration(3000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                //animatorView.setProgress(currentValue);
                view2.setProgress(currentValue);
                view3.setProgress(currentValue);

            }
        });
        anim.start();

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}
