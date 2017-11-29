package com.tzl.android_collection.custom_view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.bean.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class SchedulingView extends View {

    private List<String> menulist;   //菜单列表
    private List<String> intervalNum; //时间分割列表
    private List<UserInfo> userList; //美容师列表
    private int itemHeight=80;          //单个item的高度
    private int divisionWidth=1;       //分割线的宽度
    private int divisionColor=Color.parseColor("#686969");       //分割线的颜色
    private int menuColor=Color.parseColor("#c5a561");           //菜单颜色
    private int menuItemHeight=60;
    private int screenWidth;
    private int menuTextColor=Color.BLACK;      //菜单文字颜色
    private int menuTextSize=20;                //菜单文字大小

    private Paint divisionPaint;                //分割线画笔
    private Paint menuPaint;                    //菜单区画笔
    private Paint menuTextPaint;                //文字画笔

    private int BWScreenWidth;
    private int BWScreenHeight;

    public SchedulingView(Context context) {
        this(context,null);
    }

    public SchedulingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SchedulingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.SchedulingView);
        divisionWidth= (int) array.getDimension(R.styleable.SchedulingView_division_width,divisionWidth);
        divisionColor=array.getColor(R.styleable.SchedulingView_division_color, divisionColor);
        itemHeight= (int) array.getDimension(R.styleable.SchedulingView_item_height,itemHeight);
        menuColor=array.getColor(R.styleable.SchedulingView_menu_color,menuColor);
        menuItemHeight= (int) array.getDimension(R.styleable.SchedulingView_menuitem_height,menuItemHeight);
        menuTextColor=array.getColor(R.styleable.SchedulingView_menuTextColor,menuTextColor);
        menuTextSize= (int) array.getDimension(R.styleable.SchedulingView_menuTextSize,menuTextSize);
        //初始化分割线画笔
        divisionPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        divisionPaint.setColor(divisionColor);
        divisionPaint.setStrokeWidth(divisionWidth);
        //初始化菜单背景画笔
        menuPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        menuPaint.setColor(menuColor);
        //初始化文字画笔
        menuTextPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        menuTextPaint.setColor(menuTextColor);
        menuTextPaint.setTextSize(menuTextSize);
        menuTextPaint.setTextAlign(Paint.Align.CENTER);


    }


    /**
     * 设置分割线宽度
     * @param width
     */
    public void setDivisionWidth(int width){
        divisionWidth=width;
    }
    /**
     * 设置分割线颜色
     * @param color
     */
    public void setDivisionColor(int color){
        divisionColor=color;
    }

    /**
     * 设置菜单项
     * @param menulist
     */
    public void setMenuList(List<String> menulist){
        this.menulist=menulist;
    }

    /**
     * 设置美容师数据
     * @param userList
     */
    public void setUserList(List<UserInfo> userList){
        this.userList=userList;
    }

    /**
     * 设置item的高度
     * @param itemHeight
     */
    public void setItemHeight(int itemHeight){
        this.itemHeight=itemHeight;
    }

    /**
     * 设置时间分割
     * @param intervalNum
     */
    public void setTimeintervalNum(List<String> intervalNum){
        this.intervalNum=intervalNum;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        screenWidth=MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(BWScreenWidth, BWScreenHeight);
    }
    public void setWidthHeight(int width, int height) {
        this.BWScreenHeight = height;
        this.BWScreenWidth = width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //横向的元素数量
        int transverseNum=userList.size()+1;
        int itemWid=screenWidth/transverseNum;
        //绘制菜单区域颜色
        canvas.drawRect(0,0,screenWidth,menulist.size()*menuItemHeight,menuPaint);
        //绘制横向分割线
        for (int i=0;i<menulist.size()+1;i++){
            canvas.drawLine(0,i*menuItemHeight,screenWidth,i*menuItemHeight,divisionPaint);
        }
        for (int i=1;i<intervalNum.size()+1;i++){
            canvas.drawLine(0,i*itemHeight+menuItemHeight*menulist.size(),screenWidth,i*itemHeight+menuItemHeight*menulist.size(),divisionPaint);
        }
        //绘制纵向分割线
        for (int i=0;i<transverseNum+1;i++){
            canvas.drawLine(i*itemWid,0,i*itemWid,itemHeight*intervalNum.size()+menuItemHeight*menulist.size(),divisionPaint);
        }
        //绘制菜单文字
        for (int i=0;i<menulist.size();i++){
            canvas.drawText(menulist.get(i),itemWid/2,menuItemHeight*i+menuItemHeight/2+menuTextSize/2,menuTextPaint);
        }
        //绘制美容师信息
        for (int i=0;i<userList.size();i++){
            canvas.drawText(userList.get(i).getName(),itemWid/2+itemWid*(i+1),menuItemHeight/2+menuTextSize/2,menuTextPaint);
            canvas.drawText(userList.get(i).getShift(),itemWid/2+itemWid*(i+1),menuItemHeight*1+menuItemHeight/2+menuTextSize/2,menuTextPaint);
            canvas.drawText(userList.get(i).getSingleRow()+"",itemWid/2+itemWid*(i+1),menuItemHeight*2+menuItemHeight/2+menuTextSize/2,menuTextPaint);
            canvas.drawText(userList.get(i).getOnLineNum()+"",itemWid/2+itemWid*(i+1),menuItemHeight*3+menuItemHeight/2+menuTextSize/2,menuTextPaint);
            canvas.drawText(userList.get(i).getOnDownNum()+"",itemWid/2+itemWid*(i+1),menuItemHeight*4+menuItemHeight/2+menuTextSize/2,menuTextPaint);
        }
        for (int i=0;i<intervalNum.size();i++){
            canvas.drawText(intervalNum.get(i),itemWid/2,menulist.size()*menuItemHeight+itemHeight*i+itemHeight/2+menuTextSize/2,menuTextPaint);
        }
        for (int i=0;i<userList.size();i++){
            if (userList.get(i).getStartPosition()!=0){
                canvas.drawRect(itemWid*(i+1),menulist.size()*menuItemHeight+itemHeight*userList.get(i).getStartPosition(),itemWid*(i+2),menulist.size()*menuItemHeight+itemHeight*userList.get(i).getEndPosition(),menuPaint);
               // canvas.drawText("暂时不填测试数据",itemWid*(i+1)+itemWid/2,menulist.size()*menuItemHeight+itemHeight*userList.get(i).getEndPosition(),menuTextPaint);
            }
        }

    }
}
