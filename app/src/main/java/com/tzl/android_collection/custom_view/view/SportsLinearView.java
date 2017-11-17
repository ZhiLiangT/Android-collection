package com.tzl.android_collection.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 进度条 线性
 * Created by tianzl on 2017/11/3.
 */

public class SportsLinearView extends View {

    float progress=0;
    Paint paint;
    int padd=20;
    int textSize=100;
    Shader shader;

    public SportsLinearView(Context context) {
        this(context,null);
    }

    public SportsLinearView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SportsLinearView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(246, 33, 95));
        paint.setTextSize(40);
        paint.setStrokeCap(Paint.Cap.ROUND);

    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int wid=getWidth();
        int hei=getHeight();
        int x=Math.min(wid,hei);
        if (shader==null){
            shader=new LinearGradient(0,0,getWidth(),getHeight(),
                    Color.parseColor("#E91E63"),Color.parseColor("#2196F3"),Shader.TileMode.CLAMP);
        }
        paint.setShader(shader);
        paint.setStrokeWidth(35);
        canvas.drawRect(0,hei/2-x/2+padd,(wid-textSize)*progress/100,(hei+x)/2-padd,paint);
        canvas.drawText((int)progress+"%",(wid-textSize)*progress/100,hei/2-(paint.ascent() + paint.descent()) / 2,paint);
    }
}
