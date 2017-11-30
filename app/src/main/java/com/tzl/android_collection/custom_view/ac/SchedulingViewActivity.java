package com.tzl.android_collection.custom_view.ac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.adapter.TestRVAdapter;
import com.tzl.android_collection.custom_view.bean.Scheduling;
import com.tzl.android_collection.custom_view.bean.UserInfo;
import com.tzl.android_collection.custom_view.dialog.ShiftAdjustDialog;
import com.tzl.android_collection.custom_view.view.HorizontalSchedulingView;

import java.util.ArrayList;
import java.util.List;

public class SchedulingViewActivity extends AppCompatActivity {
    private HorizontalSchedulingView schedulingView;
    private List<String> listMenu;
    private List<UserInfo> userInfos;
    private List<String> intervalNum;

    private RecyclerView rvHorizontal;
    private TestRVAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling_view);
        init();
        schedulingView= (HorizontalSchedulingView) findViewById(R.id.test_scheduling);
        schedulingView.setMenuList(listMenu);
        schedulingView.setUserList(userInfos);
        schedulingView.setTimeintervalNum(intervalNum);
        rvHorizontal= (RecyclerView) findViewById(R.id.test_rv_horizontal);
        initData();
        initEvent();

    }
    private void initData() {
        List<String> list=new ArrayList<>();
        list.addAll(listMenu);
        list.addAll(intervalNum);
        adapter=new TestRVAdapter(this,list);
        rvHorizontal.setLayoutManager(new LinearLayoutManager(this));
        rvHorizontal.setAdapter(adapter);
    }
    private void initEvent() {

        schedulingView.setOnSchedulingClickListener(new HorizontalSchedulingView.setOnSchedulingClickListener() {

            @Override
            public void onMenuShift(final UserInfo userInfo, final int position) {
                //班次调整点击事件
                ShiftAdjustDialog dialog=new ShiftAdjustDialog();
                dialog.show(getFragmentManager(),"dialog_shift");
                dialog.setOnDialogShiftClickListener(new ShiftAdjustDialog.OnDialogShiftClickListener() {
                    @Override
                    public void onConfirm(String msg, int pos) {
                        userInfos.get(position).setShift(msg);
                        schedulingView.setUserList(userInfos);
                    }
                });
            }
            @Override
            public void onOrderDetails(UserInfo userInfo, Scheduling scheduling) {
                //预约详情点击事件
                Toast.makeText(SchedulingViewActivity.this,"userInfo :name "+userInfo.getName()+" Scheduling:"+scheduling.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void init(){
        listMenu=new ArrayList<>();
        listMenu.add("美容师");
        listMenu.add("班次");
        listMenu.add("排单");
        listMenu.add("线上点单");
        listMenu.add("线下点单");
        userInfos=new ArrayList<>();
        List<Scheduling> schedulings=new ArrayList<>();
        schedulings.add(new Scheduling(10,13,1));
        schedulings.add(new Scheduling(1,4,2));
        List<Scheduling> schedulings2=new ArrayList<>();
        schedulings2.add(new Scheduling(5,8,4));
        List<Scheduling> schedulings3=new ArrayList<>();
        schedulings3.add(new Scheduling(3,6,4));
        schedulings3.add(new Scheduling(9,12,3));
        List<Scheduling> schedulings4=new ArrayList<>();
        schedulings4.add(new Scheduling(4,7,1));
        List<Scheduling> schedulings5=new ArrayList<>();
        schedulings5.add(new Scheduling(1,4,2));
        List<Scheduling> schedulings6=new ArrayList<>();
        schedulings6.add(new Scheduling(6,9,4));
        List<Scheduling> schedulings7=new ArrayList<>();
        schedulings7.add(new Scheduling(11,14,3));
        userInfos.add(new UserInfo("小明","早班",10,20,10,schedulings));
        userInfos.add(new UserInfo("老王","晚班",10,20,10,schedulings2));
        userInfos.add(new UserInfo("小李","中班",10,20,10,schedulings3));
        userInfos.add(new UserInfo("老张","加班",10,20,10,schedulings4));
        userInfos.add(new UserInfo("老李","加班",10,20,10,schedulings5));
        userInfos.add(new UserInfo("小强","加班",10,20,10,schedulings6));
        userInfos.add(new UserInfo("大熊","加班",10,20,10,schedulings7));
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
        intervalNum.add("14:30");
        intervalNum.add("15:00");
        intervalNum.add("15:30");
        intervalNum.add("16:00");
        intervalNum.add("16:30");
        intervalNum.add("17:00");

    }
}
