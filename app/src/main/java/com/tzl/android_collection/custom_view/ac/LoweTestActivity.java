package com.tzl.android_collection.custom_view.ac;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.custom_view.view.LoweImageView;

public class LoweTestActivity extends BaseActivity implements OnClickListener {
    private EditText editText;
    private LoweImageView imageView;
    private SeekBar seekBar;
    private Button bt;
    private int width;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_lowe_test);
    }

    @Override
    public void initView() {
        editText= (EditText) findViewById(R.id.lowe_et);
        imageView= (LoweImageView) findViewById(R.id.lowe_icon);
        seekBar= (SeekBar) findViewById(R.id.lowe_seekbar);
        bt= (Button) findViewById(R.id.lowe_bt);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                width=Integer.parseInt(editText.getText().toString());
                imageView.setRatio(5);
                imageView.setWidth(width);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
