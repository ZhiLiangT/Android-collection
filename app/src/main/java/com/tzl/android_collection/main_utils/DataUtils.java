package com.tzl.android_collection.main_utils;

import com.tzl.android_collection.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianzl on 2017/11/7.
 */

public class DataUtils {
    public static final String [] mainNames=new String[]{"动画","自定义View",
            "数据库","第三方框架","图片处理",
            "多媒体","重要机制","第三方SDK","新特性"};
    public static final int [] mainResId={R.mipmap.classify_1,R.mipmap.classify_2,R.mipmap.classify_3
            ,R.mipmap.classify_4,R.mipmap.classify_5,R.mipmap.classify_6,R.mipmap.classify_7,R.mipmap.classify_8,
            R.mipmap.classify_9};

    public static List<ClassifyBean> getClassifyList(){
        List<ClassifyBean> list=new ArrayList<>();
        list.add(new ClassifyBean(mainNames[0],mainResId[0],0));
        list.add(new ClassifyBean(mainNames[1],mainResId[1],1));
        list.add(new ClassifyBean(mainNames[2],mainResId[2],2));
        list.add(new ClassifyBean(mainNames[3],mainResId[3],3));
        list.add(new ClassifyBean(mainNames[4],mainResId[4],4));
        list.add(new ClassifyBean(mainNames[5],mainResId[5],5));
        list.add(new ClassifyBean(mainNames[6],mainResId[6],6));
        list.add(new ClassifyBean(mainNames[7],mainResId[7],7));
        list.add(new ClassifyBean(mainNames[8],mainResId[8],8));
        return list;
    }
}
