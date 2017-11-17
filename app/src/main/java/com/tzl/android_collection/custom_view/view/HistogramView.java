package com.tzl.android_collection.custom_view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.bean.LauguateInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * 柱状图
 * Created by tianzl on 2017/11/1.
 */

public class HistogramView extends View {

    private List<LauguateInfo> mData;
    private Paint paintHis;
    private Paint paintTitle;

    private int hisColor=0xFF2EAF79;    //柱状图颜色
    private int lineColor=0xFFCCCCCC;   //坐标线颜色
    private int titleColor=0xFFCCCCCC;  //柱状体底部title字体颜色
    private String nameX="Y轴";         //X轴方向名称
    private String nameY="X轴";         //Y轴方向名称
    private int hisMax=100;             //柱状图最大高度
    private float titleSize=10;         //柱状体底部title字体大小
    private int levelNum=5;             //水平线的数量


    public HistogramView(Context context) {
        this(context,null);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics metrics=getResources().getDisplayMetrics();
        titleSize= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,titleSize,metrics);
        //获取自定义View属性
        TypedArray typedArray=context.getTheme().
                obtainStyledAttributes(attrs, R.styleable.Histogram_View,defStyleAttr,0);
        hisColor=typedArray.getColor(R.styleable.Histogram_View_histogramColor,hisColor);
        nameX=typedArray.getString(R.styleable.Histogram_View_histogtamNameX);
        nameY=typedArray.getString(R.styleable.Histogram_View_histogtamNameY);
        hisMax=typedArray.getInt(R.styleable.Histogram_View_histogramMax,hisMax);
        titleSize=typedArray.getDimension(R.styleable.Histogram_View_histogtamTitleSize,titleSize);
        titleColor=typedArray.getColor(R.styleable.Histogram_View_histogtamTitleColor,titleColor);
        lineColor=typedArray.getColor(R.styleable.Histogram_View_histogtamLineColor,lineColor);
        levelNum=typedArray.getInt(R.styleable.Histogram_View_histogtamLevelNum,5);

        paintHis=new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHis.setColor(lineColor);
        paintHis.setTextSize(titleSize);

        paintTitle=new Paint(Paint.ANTI_ALIAS_FLAG);
        paintTitle.setColor(hisColor);
        paintTitle.setTextSize(titleSize);

        jiaData();
    }
    public void jiaData(){
        mData=new ArrayList<>();
        mData.add(new LauguateInfo(50,"Py"));
        mData.add(new LauguateInfo(80,"java"));
        mData.add(new LauguateInfo(20,"R"));
        mData.add(new LauguateInfo(40,"Vue"));
        mData.add(new LauguateInfo(70,"C"));
        mData.add(new LauguateInfo(10,"C++"));
        mData.add(new LauguateInfo(90,"PHP"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(40,0,40,300,paintHis);
        int lineWid=85*mData.size()+90;
        canvas.drawLine(0,300,lineWid,300,paintHis);
        paintHis.setColor(0xFF393936);
        paintHis.setTextAlign(Paint.Align.RIGHT);
        for (int i=0;i<levelNum;i++){
            canvas.drawLine(40,300/levelNum*i,lineWid,300/levelNum*i,paintHis);
            if (i!=0){
                canvas.drawText(200-i*200/levelNum+"",35,300/levelNum*i+8,paintHis);
            }
        }
        paintHis.setTextAlign(Paint.Align.CENTER);
        paintHis.setColor(titleColor);
        for (int i=0;i<mData.size();i++){
            canvas.drawText(mData.get(i).getName(),85*i+95,320,paintHis);
            int hei=mData.get(i).getNumber()*2;
            canvas.drawRect(85*i+65,300-hei,85*(i+1)+40,300,paintTitle);
        }

    }
}
