package com.tzl.android_collection.multi_media.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tianzl on 2017/11/7.
 */

public class AudioBean implements Parcelable {
    private String fileName;
    private String title;
    private int duration;
    private String singer;
    private String album;
    private String year;
    private String type;
    private String size;
    private String fileUrl;
    public AudioBean(){}

    public AudioBean(String fileName, String title, int duration, String singer, String album, String year, String type, String size, String fileUrl) {
        this.fileName = fileName;
        this.title = title;
        this.duration = duration;
        this.singer = singer;
        this.album = album;
        this.year = year;
        this.type = type;
        this.size = size;
        this.fileUrl = fileUrl;
    }


    @Override
    public String toString() {
        return "AudioBean{" +
                "fileName='" + fileName + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", singer='" + singer + '\'' +
                ", album='" + album + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fileName);
        dest.writeString(this.title);
        dest.writeInt(this.duration);
        dest.writeString(this.singer);
        dest.writeString(this.album);
        dest.writeString(this.year);
        dest.writeString(this.type);
        dest.writeString(this.size);
        dest.writeString(this.fileUrl);
    }

    protected AudioBean(Parcel in) {
        this.fileName = in.readString();
        this.title = in.readString();
        this.duration = in.readInt();
        this.singer = in.readString();
        this.album = in.readString();
        this.year = in.readString();
        this.type = in.readString();
        this.size = in.readString();
        this.fileUrl = in.readString();
    }

    public static final Parcelable.Creator<AudioBean> CREATOR = new Parcelable.Creator<AudioBean>() {
        @Override
        public AudioBean createFromParcel(Parcel source) {
            return new AudioBean(source);
        }

        @Override
        public AudioBean[] newArray(int size) {
            return new AudioBean[size];
        }
    };
}
