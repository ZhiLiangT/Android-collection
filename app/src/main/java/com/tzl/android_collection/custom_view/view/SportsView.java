package com.tzl.android_collection.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tianzl on 2017/11/3.
 */

public class SportsView extends View {

    float progress=0;
    Paint paint;
    RectF rectF;
    int padd=20;
    Shader shader;
    public SportsView(Context context) {
        this(context,null);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
            shader=new LinearGradient(0,0,wid,hei,
                    Color.parseColor("#E91E63"),Color.parseColor("#2196F3"),Shader.TileMode.CLAMP);
        }
        paint.setShader(shader);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(35);
        if (rectF==null){
            rectF=new RectF(wid/2-x/2+padd,hei/2-x/2+padd,(wid+x)/2-padd,(hei+x)/2-padd);
        }
        canvas.drawArc(rectF, 135, progress * 2.7f, false, paint);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText((int)progress+"%",wid/2,hei/2-(paint.ascent() + paint.descent()) / 2,paint);
    }
}
