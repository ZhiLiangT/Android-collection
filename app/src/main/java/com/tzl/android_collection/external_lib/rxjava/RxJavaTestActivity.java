package com.tzl.android_collection.external_lib.rxjava;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaTestActivity extends BaseActivity implements View.OnClickListener{
    private Button btEasy1;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_rx_java_test);
    }

    @Override
    public void initView() {
        btEasy1= (Button) findViewById(R.id.rxjava_easy_1);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        btEasy1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rxjava_easy_1:
                Test1();
                break;
        }
    }
    //简单练习1
    public void Test1(){
        Observable<String> observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("one");
                e.onNext("two");
                e.onNext("three");
                e.onComplete();
            }
        });
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.v("RxJavaTestActivity","value : "+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        //关联起来
        observable.subscribe(observer);
    }
    //简单练习2
    public void Test2(){

    }


}
