package com.tzl.android_collection.custom_view.bean;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class Scheduling {
    int startPosition;
    int endPosition;
    int type;//用户类型 1：线下点单 2：排单 3：新客 4：线上点单

    public Scheduling() {
    }

    public Scheduling(int startPosition, int endPosition, int type) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Scheduling{" +
                "startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                ", type=" + type +
                '}';
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
