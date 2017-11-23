package com.tzl.android_collection.animator;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.animator.recycler_effect.RecyclerViewEffectActivity;

/**
 * Android动画
 */
public class AnimatorActivity extends BaseActivity implements View.OnClickListener {

    private Button bt1;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_animator);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.anim_bt1);
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
        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.anim_bt1:
                Intent intentRV=new Intent(this, RecyclerViewEffectActivity.class);
                startActivity(intentRV);
                break;
        }
    }
}
