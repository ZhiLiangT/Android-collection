package com.tzl.android_collection.custom_view.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.tzl.android_collection.R;

/**
 * 等比例控件
 */
public class LoweImageView extends android.support.v7.widget.AppCompatImageView {
    /** 
     * 图片比例. 比例=宽/高 
     */  
    private float mRatio;
    private int width;
  
    public LoweImageView(Context context) {  
        this(context, null);  
    }  
  
    public LoweImageView(Context context, AttributeSet attrs) {  
        this(context, attrs, 0);  
    }  
  
    public LoweImageView(Context context, AttributeSet attrs, int defStyleAttr) {  
        super(context, attrs, defStyleAttr);  
        init(context, attrs);  
    }  
  
    /** 
     * 初始化 
     *  
     * @param context 
     *            上下文 
     * @param attrs 
     *            属性 
     */  
    private void init(Context context, AttributeSet attrs) {  
        TypedArray typedArray = context.obtainStyledAttributes(attrs,  
                R.styleable.LoweImageView);
        mRatio = typedArray.getFloat(R.styleable.LoweImageView_ratio, 0);  
        typedArray.recycle();  
    }
    public void setRatio(float ratio){
        mRatio=ratio;
        invalidate();
    }
    public void setWidth(int wid){
        width=wid;
        invalidate();
    }
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // 宽模式  
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);  
        // 宽大小  
        width = MeasureSpec.getSize(widthMeasureSpec);
        // 高大小  
        int heightSize;  
        // 只有宽的值是精确的才对高做精确的比例校对  
        if (widthMode == MeasureSpec.EXACTLY && mRatio > 0) {  
            heightSize = (int) (width / mRatio + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,  
                    MeasureSpec.EXACTLY);  
        }  
        super.onMeasure(width, heightMeasureSpec);
    }  
}  