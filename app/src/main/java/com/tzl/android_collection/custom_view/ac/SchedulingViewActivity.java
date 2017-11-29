package com.tzl.android_collection.custom_view.ac;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.bean.UserInfo;
import com.tzl.android_collection.custom_view.view.SchedulingView;

import java.util.ArrayList;
import java.util.List;

public class SchedulingViewActivity extends AppCompatActivity {
    private SchedulingView schedulingView;
    private List<String> listMenu;
    private List<UserInfo> userInfos;
    private List<String> intervalNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling_view);
        init();
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        schedulingView= (SchedulingView) findViewById(R.id.main_SchedulingView);
        schedulingView.setWidthHeight(width,height);
        schedulingView.setMenuList(listMenu);
        schedulingView.setUserList(userInfos);
        schedulingView.setTimeintervalNum(intervalNum);

    }
    public void init(){
        listMenu=new ArrayList<>();
        listMenu.add("美容师");
        listMenu.add("班次");
        listMenu.add("排单");
        listMenu.add("线上点单");
        listMenu.add("线下点单");
        userInfos=new ArrayList<>();
        userInfos.add(new UserInfo("小明","早班",10,20,10,5,7));
        userInfos.add(new UserInfo("老王","晚班",10,20,10,2,4));
        userInfos.add(new UserInfo("小李","中班",10,20,10,1,3));
        userInfos.add(new UserInfo("老张","加班",10,20,10,4,6));
        intervalNum=new ArrayList<>();
        intervalNum.add("09:30");
        intervalNum.add("10:00");
        intervalNum.add("10:30");
        intervalNum.add("11:00");
        intervalNum.add("11:30");
        intervalNum.add("12:00");
        intervalNum.add("12:30");
        intervalNum.add("13:00");
        intervalNum.add("13:30");
        intervalNum.add("14:00");

    }
}
