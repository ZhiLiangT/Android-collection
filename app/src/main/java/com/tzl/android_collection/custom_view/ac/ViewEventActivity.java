package com.tzl.android_collection.custom_view.ac;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.Scroller;
import android.widget.TextView;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.view.EventView;

/**
 * View事件处理
 */
public class ViewEventActivity extends BaseActivity {

    EventView tv1;
    TextView tv2;
    VelocityTracker tracker;
    Scroller scroller;
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_view_event);
    }

    @Override
    public void initView() {
        tv1=(EventView) findViewById(R.id.view_event_tv1);
        tv2=(TextView) findViewById(R.id.view_event_tv2);
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (tracker==null){
            tracker=VelocityTracker.obtain();
        }
        tracker.addMovement(event);
        tracker.computeCurrentVelocity(500);
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int xVelocity= (int) tracker.getXVelocity();
                int yVelocity= (int) tracker.getYVelocity();
                tv2.setText("x轴速度: "+xVelocity+" y轴速度: "+yVelocity);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void initEvent() {

    }
}
