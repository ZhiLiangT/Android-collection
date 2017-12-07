package com.tzl.android_collection.common.ac;


import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

public class SpanTestActivity extends BaseActivity {

    private TextView tv1,tv2,tv3,tv4,tv5,tv6;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_span_test);
    }

    @Override
    public void initView() {
        tv1= (TextView) findViewById(R.id.span_tv1);
        tv2= (TextView) findViewById(R.id.span_tv2);
        tv3= (TextView) findViewById(R.id.span_tv3);
        tv4= (TextView) findViewById(R.id.span_tv4);
        tv5= (TextView) findViewById(R.id.span_tv5);
        tv6= (TextView) findViewById(R.id.span_tv6);
    }

    @Override
    public void initData() {
        setForegroundColor();
        setBackgroundColor();
        setLink();
    }

    @Override
    public void initEvent() {

    }
    /**
     * 设置不同颜色文字
     */
    private void setForegroundColor() {
        SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");

        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv1.setText(spannableString);
    }
    /**
     * 设置背景色
     */
    private void setBackgroundColor() {
        SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");

        spannableString.setSpan(new BackgroundColorSpan(Color.RED), 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv2.setText(spannableString);
    }
    /**
     * 设置超链接
     */
    private void setLink() {
        SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");
        //设置下划线
        spannableString.setSpan(new UnderlineSpan(), 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv3.setText(spannableString);
    }


}
