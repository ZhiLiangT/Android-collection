package com.tzl.android_collection.external_lib.retrofit2;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.external_lib.retrofit2.bean.Test;
import com.tzl.android_collection.external_lib.retrofit2.inter.AppURL;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Retrofit2TestActivity extends BaseActivity implements View.OnClickListener{
    private Button bt1,bt2;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_retrofit2_test);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.retrofit_bt1);
        bt2= (Button) findViewById(R.id.retrofit_bt2);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.retrofit_bt1:
                test1();
                break;
            case R.id.retrofit_bt2:
                break;
        }
    }
    public void test1(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gank.io/").build();
        AppURL appURL=retrofit.create(AppURL.class);
        Call<ResponseBody> call=appURL.getAndroidInfo();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result=response.body().string();
                    Log.i("Retrofit2TestActivity","返回值"+result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void test2(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gank.io/").build();
        AppURL appURL=retrofit.create(AppURL.class);
        Call<Test> call=appURL.getTestInfo();
        call.enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
                Test.ResultsBean bean=response.body().getResults().get(0);

            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {

            }
        });
    }
}
