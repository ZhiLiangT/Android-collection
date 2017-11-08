package com.tzl.android_collection.main_utils;

/**
 * Created by tianzl on 2017/11/7.
 */

public class ClassifyBean {
    private String name;
    private int redId;
    private int type;

    public ClassifyBean(){}

    @Override
    public String toString() {
        return "ClassifyBean{" +
                "name='" + name + '\'' +
                ", redId=" + redId +
                '}';
    }

    public ClassifyBean(String name, int redId, int type) {
        this.name = name;
        this.redId = redId;
        this.type = type;
    }

    public ClassifyBean(String name, int redId) {
        this.name = name;
        this.redId = redId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRedId() {
        return redId;
    }

    public void setRedId(int redId) {
        this.redId = redId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
