package com.tzl.android_collection.custom_view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.bean.Scheduling;
import com.tzl.android_collection.custom_view.bean.UserInfo;

import java.util.List;

/**
 * 预约表自定义控件
 * Created by Administrator on 2017/11/29 0029.
 */

public class SchedulingView extends View {

    private List<String> menulist;                          //菜单列表
    private List<String> intervalNum;                       //时间分割列表
    private List<UserInfo> userList;                        //美容师列表
    private int itemHeight=80;                              //单个item的高度
    private int divisionWidth=1;                            //分割线的宽度
    private int divisionColor=Color.parseColor("#686969");  //分割线的颜色
    private int menuColor=Color.parseColor("#c5a561");      //菜单颜色
    private int menuItemHeight=60;
    private int screenWidth;
    private int menuTextColor=Color.BLACK;                  //菜单文字颜色
    private int menuTextSize=20;                            //菜单文字大小

    private Paint divisionPaint;                            //分割线画笔
    private Paint menuPaint;                                //菜单区画笔
    private Paint menuTextPaint;                            //文字画笔
    private TextPaint textPaint;                            //预约记录画笔

    private int BWScreenWidth;                              //控件宽度
    private int BWScreenHeight;

    private boolean menuItemClickState=true;                //菜单项点击事件开关



    public SchedulingView(Context context) {
        this(context,null);
    }

    public SchedulingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SchedulingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initArrts(context,attrs);
        initPaint();
        setClickable(true);
    }

    /**
     * 初始化属性
     */
    private void initArrts(Context context,AttributeSet attrs) {
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.SchedulingView);
        divisionWidth= (int) array.getDimension(R.styleable.SchedulingView_division_width,divisionWidth);
        divisionColor=array.getColor(R.styleable.SchedulingView_division_color, divisionColor);
        itemHeight= (int) array.getDimension(R.styleable.SchedulingView_item_height,itemHeight);
        menuColor=array.getColor(R.styleable.SchedulingView_menu_color,menuColor);
        menuItemHeight= (int) array.getDimension(R.styleable.SchedulingView_menuitem_height,menuItemHeight);
        menuTextColor=array.getColor(R.styleable.SchedulingView_menuTextColor,menuTextColor);
        menuTextSize= (int) array.getDimension(R.styleable.SchedulingView_menuTextSize,menuTextSize);
    }
    /**
     * 初始化画笔
     */
    private void initPaint() {
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
        //初始化预约记录画笔
        textPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(menuTextSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 设置分割线宽度
     */
    public void setDivisionWidth(int width){
        divisionWidth=width;
    }
    /**
     * 设置分割线颜色
     */
    public void setDivisionColor(int color){
        divisionColor=color;
    }

    /**
     * 设置菜单项
     */
    public void setMenuList(List<String> menulist){
        this.menulist=menulist;
    }

    /**
     * 设置美容师数据
     */
    public void setUserList(List<UserInfo> userList){
        this.userList=userList;
        invalidate();
    }

    /**
     * 设置item的高度
     */
    public void setItemHeight(int itemHeight){
        this.itemHeight=itemHeight;
    }

    /**
     * 设置时间分割
     */
    public void setTimeintervalNum(List<String> intervalNum){
        this.intervalNum=intervalNum;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        screenWidth=MeasureSpec.getSize(widthMeasureSpec);
        int height=menuItemHeight*menulist.size()+itemHeight*intervalNum.size();
        setMeasuredDimension(BWScreenWidth, height);
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
        menuPaint.setColor(menuColor);
        canvas.drawRect(0,0,screenWidth,menulist.size()*menuItemHeight,menuPaint);
        //绘制菜单部分横向分割线
        for (int i=0;i<menulist.size()+1;i++){
            canvas.drawLine(0,i*menuItemHeight,screenWidth,i*menuItemHeight,divisionPaint);
        }
        //绘制预约部分横向分割线
        for (int i=1;i<intervalNum.size()+1;i++){
            canvas.drawLine(0,i*itemHeight+menuItemHeight*menulist.size(),screenWidth,i*itemHeight+menuItemHeight*menulist.size(),divisionPaint);
        }
        //绘制菜单文字
        for (int i=0;i<menulist.size();i++){
            canvas.drawText(menulist.get(i),itemWid/2,menuItemHeight*i+menuItemHeight/2+menuTextSize/2,menuTextPaint);
        }
        //绘制美容师信息
        for (int i=0;i<userList.size();i++){
            canvas.drawText(userList.get(i).getName(),itemWid/2+itemWid*(i+1),menuItemHeight/2+menuTextSize/2,menuTextPaint);
            canvas.drawText(userList.get(i).getShift(),itemWid/2+itemWid*(i+1),menuItemHeight+menuItemHeight/2+menuTextSize/2,menuTextPaint);
            canvas.drawText(userList.get(i).getSingleRow()+"",itemWid/2+itemWid*(i+1),menuItemHeight*2+menuItemHeight/2+menuTextSize/2,menuTextPaint);
            canvas.drawText(userList.get(i).getOnLineNum()+"",itemWid/2+itemWid*(i+1),menuItemHeight*3+menuItemHeight/2+menuTextSize/2,menuTextPaint);
            canvas.drawText(userList.get(i).getOnDownNum()+"",itemWid/2+itemWid*(i+1),menuItemHeight*4+menuItemHeight/2+menuTextSize/2,menuTextPaint);
        }
        //绘制时间分割集合
        for (int i=0;i<intervalNum.size();i++){
            canvas.drawText(intervalNum.get(i),itemWid/2,menulist.size()*menuItemHeight+itemHeight*i+itemHeight/2+menuTextSize/2,menuTextPaint);
        }
        //绘制预约信息
        for (int i=0;i<userList.size();i++){
            List<Scheduling> list=userList.get(i).getSchedulingList();
            for (Scheduling scheduling:list){
                int start=scheduling.getStartPosition();
                int end =scheduling.getEndPosition();
                int type=scheduling.getType();
                if (type==1){
                    menuPaint.setColor(Color.parseColor("#b3ffb3"));
                }else if (type==2){
                    menuPaint.setColor(Color.parseColor("#ffccff"));
                }else if (type==3){
                    menuPaint.setColor(Color.parseColor("#99ddff"));
                }else if (type==4){
                    menuPaint.setColor(Color.parseColor("#ffe6cc"));
                }
                //绘制矩形区
                canvas.drawRect(itemWid*(i+1),menulist.size()*menuItemHeight+itemHeight*start,itemWid*(i+2),menulist.size()*menuItemHeight+itemHeight*end,menuPaint);
                //绘制文字信息
                StaticLayout staticLayout=new StaticLayout("025012\nY000015\n莉莉",textPaint,itemWid, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
                canvas.save();
                canvas.translate(itemWid*(i+1)+itemWid/2,menulist.size()*menuItemHeight+itemHeight*start+((end-start)*itemHeight-staticLayout.getHeight())/2);
                staticLayout.draw(canvas);
                canvas.restore();
            }
        }
        //绘制纵向分割线
        for (int i=0;i<transverseNum+1;i++){
            canvas.drawLine(i*itemWid,0,i*itemWid,itemHeight*intervalNum.size()+menuItemHeight*menulist.size(),divisionPaint);
        }
    }
    //回调点击事件
    interface setOnSchedulingClickListener{
        //班次调整
        void onMenuShift(UserInfo userInfo, int position);
        //预约排班的详情
        void onOrderDetails(UserInfo userInfo, Scheduling scheduling);

    }
    private setOnSchedulingClickListener listener;

    public void setOnSchedulingClickListener(setOnSchedulingClickListener listener){
        this.listener=listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float startShiftX;
        float startShiftY;
        int transverseNum=userList.size()+1;
        int itemWid=screenWidth/transverseNum;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startShiftX=event.getX();
                startShiftY=event.getY();
                menuItemClickState=false;
                if (startShiftY>menuItemHeight&&startShiftY<menuItemHeight*2){
                    if (startShiftX>itemWid&&startShiftX<screenWidth){
                        int index= (int) ((startShiftX-itemWid)/itemWid);
                        listener.onMenuShift(userList.get(index),index);
                    }
                }
                if (startShiftX>itemWid&&startShiftY>menuItemHeight*menulist.size()){
                    //该美容师所处的列的位置
                    int userColumn= (int) ((startShiftX-itemWid)/itemWid);
                    //预约列表的行的位置
                    int userRow= (int) ((startShiftY-(menuItemHeight*menulist.size()))/itemHeight)+1;
                    List<Scheduling> list=userList.get(userColumn).getSchedulingList();
                    for (int i=0;i<list.size();i++){
                        if (userRow>list.get(i).getStartPosition()&&userRow<list.get(i).getEndPosition()+1){
                            listener.onOrderDetails(userList.get(userColumn),list.get(i));
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }
}
