package com.tzl.android_collection.custom_view.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by tianzl on 2017/11/23.
 */

public class EventView extends View {

    VelocityTracker tracker;

    private static final String TAG = "EventView";
    Scroller mScroller;

    public EventView(Context context) {
        this(context,null);
    }

    public EventView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
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
                int yVelocity= (int) tracker.getXVelocity();
                Log.i(TAG, "onTouch: x 速度 "+xVelocity);
                Log.i(TAG, "onTouch: y 速度 "+yVelocity);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void smoothScroll(int destX, int destY) {
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;
        mScroller.startScroll(scrollX, 0, deltaX, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

}
