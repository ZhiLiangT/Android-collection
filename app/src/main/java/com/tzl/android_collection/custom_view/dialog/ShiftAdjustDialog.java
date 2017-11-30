package com.tzl.android_collection.custom_view.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.adapter.DialogShiftAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 班次调整Dialog
 * Created by Administrator on 2017/11/30 0030.
 */

public class ShiftAdjustDialog extends DialogFragment{

    private RecyclerView recyclerView;
    private Button btCancel;
    private Button btConfirm;
    private List<String> list;
    private DialogShiftAdapter adapter;
    private String message="早班";
    private int position=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.dialog_shift_adjust,container,false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new DialogShiftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String msg, int pos) {
                message=msg;
                position=pos;
            }
        });
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onConfirm(message,position);
                dismiss();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    public interface OnDialogShiftClickListener{
        void onConfirm(String msg, int pos);
    }
    private OnDialogShiftClickListener listener;
    public void setOnDialogShiftClickListener(OnDialogShiftClickListener listener){
        this.listener=listener;
    }

    private void initData() {
        list=new ArrayList<>();
        list.add("早班");
        list.add("周末早班");
        list.add("中班");
        list.add("周末中班");
        list.add("晚班");
        list.add("周末晚班");
        list.add("加班");
        list.add("休息");
        adapter=new DialogShiftAdapter(getActivity(),list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
    }


    private void initView(View view) {
        recyclerView=view.findViewById(R.id.dialog_shift_rv);
        btCancel=view.findViewById(R.id.dialog_shift_bt_cancel);
        btConfirm=view.findViewById(R.id.dialog_shift_bt_confirm);
    }
}
