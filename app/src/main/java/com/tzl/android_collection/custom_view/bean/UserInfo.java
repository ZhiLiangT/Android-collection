package com.tzl.android_collection.custom_view.bean;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class UserInfo {
    private String name;    //名字
    private String Shift;   //班次
    private int SingleRow;   //排单
    private int onLineNum;   //线上点单
    private int onDownNum;   //线下点单
    private int startPosition;  //开始位置
    private int endPosition;   //结束位置
    public UserInfo(){}

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", Shift='" + Shift + '\'' +
                ", SingleRow=" + SingleRow +
                ", onLineNum=" + onLineNum +
                ", onDownNum=" + onDownNum +
                ", startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                '}';
    }

    public UserInfo(String name, String shift, int singleRow, int onLineNum, int onDownNum, int startPosition, int endPosition) {
        this.name = name;
        Shift = shift;
        SingleRow = singleRow;
        this.onLineNum = onLineNum;
        this.onDownNum = onDownNum;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public UserInfo(String name, String shift, int singleRow, int onLineNum, int onDownNum) {
        this.name = name;
        Shift = shift;
        SingleRow = singleRow;
        this.onLineNum = onLineNum;
        this.onDownNum = onDownNum;
    }


    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShift() {
        return Shift;
    }

    public void setShift(String shift) {
        Shift = shift;
    }

    public int getSingleRow() {
        return SingleRow;
    }

    public void setSingleRow(int singleRow) {
        SingleRow = singleRow;
    }

    public int getOnLineNum() {
        return onLineNum;
    }

    public void setOnLineNum(int onLineNum) {
        this.onLineNum = onLineNum;
    }

    public int getOnDownNum() {
        return onDownNum;
    }

    public void setOnDownNum(int onDownNum) {
        this.onDownNum = onDownNum;
    }
}
