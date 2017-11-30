package com.tzl.android_collection.custom_view.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class UserInfo {
    private String name;    //名字
    private String Shift;   //班次
    private int SingleRow;   //排单
    private int onLineNum;   //线上点单
    private int onDownNum;   //线下点单
    private List<Scheduling> schedulingList;
    public UserInfo(){}

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", Shift='" + Shift + '\'' +
                ", SingleRow=" + SingleRow +
                ", onLineNum=" + onLineNum +
                ", onDownNum=" + onDownNum +
                ", schedulingList=" + schedulingList +
                '}';
    }

    public UserInfo(String name, String shift, int singleRow, int onLineNum, int onDownNum, List<Scheduling> schedulingList) {
        this.name = name;
        Shift = shift;
        SingleRow = singleRow;
        this.onLineNum = onLineNum;
        this.onDownNum = onDownNum;
        this.schedulingList = schedulingList;
    }

    public List<Scheduling> getSchedulingList() {
        return schedulingList;
    }

    public void setSchedulingList(List<Scheduling> schedulingList) {
        this.schedulingList = schedulingList;
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
