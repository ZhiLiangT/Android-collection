package com.tzl.android_collection.custom_view.bean;

/**
 * Created by tianzl on 2017/11/1.
 */

public class LauguateInfo {
    private int number;
    private String name;

    @Override
    public String toString() {
        return "LauguateInfo{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    public LauguateInfo(){}
    public LauguateInfo(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
