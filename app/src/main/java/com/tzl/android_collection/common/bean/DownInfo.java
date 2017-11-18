package com.tzl.android_collection.common.bean;

/**
 * Created by tianzl on 2017/11/18.
 */

public class DownInfo {

    private int resId;
    private String resUrl;
    private String resName;
    private int resState;
    private String resPath;
    private int resType;
    private int progress;
    private int resSize;


    public DownInfo() {

    }

    public DownInfo(int resId, String resUrl, String resName, int resState, String resPath, int resType) {
        this.resId = resId;
        this.resUrl = resUrl;
        this.resName = resName;
        this.resState = resState;
        this.resPath = resPath;
        this.resType = resType;
    }

    public DownInfo(int resId, String resUrl, String resName, int resState, String resPath, int resType, int progress) {
        this.resId = resId;
        this.resUrl = resUrl;
        this.resName = resName;
        this.resState = resState;
        this.resPath = resPath;
        this.resType = resType;
        this.progress = progress;
    }

    public DownInfo(int resId, String resUrl, String resName, int resState, String resPath, int resType, int progress, int resSize) {
        this.resId = resId;
        this.resUrl = resUrl;
        this.resName = resName;
        this.resState = resState;
        this.resPath = resPath;
        this.resType = resType;
        this.progress = progress;
        this.resSize = resSize;
    }

    public int getResSize() {
        return resSize;
    }

    public void setResSize(int resSize) {
        this.resSize = resSize;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public int getResState() {
        return resState;
    }

    public void setResState(int resState) {
        this.resState = resState;
    }

    public String getResPath() {
        return resPath;
    }

    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    public int getResType() {
        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }
}
