package com.tzl.android_collection.common.ac;

import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

/**
 * 文件管理
 */
public class FileManagerTestActivity extends BaseActivity implements View.OnClickListener{

    private Button bt1,bt2,bt3;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_file_manager_test);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.file_bt1);
        bt2= (Button) findViewById(R.id.file_bt2);
        bt3= (Button) findViewById(R.id.file_bt3);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.file_bt1:
                break;
            case R.id.file_bt2:
                break;
            case R.id.file_bt3:
                break;
        }
    }
}
