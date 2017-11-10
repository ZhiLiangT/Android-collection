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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaThreadControlActivity extends BaseActivity implements View.OnClickListener{

    private Button bt1;
    private Button bt2;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_rx_java_thread_control);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.rx_thread_1);
        bt2= (Button) findViewById(R.id.rx_thread_2);
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
            case R.id.rx_thread_1:
                test1();
                break;
            case R.id.rx_thread_2:
                test2();
                break;
        }
    }

    private void test2() {
        Observable<String> observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i("RxJavaThreadControl","observable:当前的线程为："+Thread.currentThread().getName());
                e.onNext("aaa");
                e.onNext("bbb");
                e.onNext("ccc");
            }
        });
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.i("RxJavaThreadControl","observer:当前的线程为："+Thread.currentThread().getName());
                Log.i("RxJavaThreadControl","value--"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.
                subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("RxJavaThreadControl","observer:当前的线程为："+Thread.currentThread().getName());
                        Log.i("RxJavaThreadControl","value--"+s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("RxJavaThreadControl","observer:当前的线程为："+Thread.currentThread().getName());
                        Log.i("RxJavaThreadControl","value--"+s);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(observer);
    }

    private void test1() {
        Observable<String> observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i("RxJavaThreadControl","Observable 所处线程"+Thread.currentThread().getName());
                e.onNext("One");
                e.onNext("Two");
                e.onNext("Three");
            }
        });
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.i("RxJavaThreadControl","Observer 所处线程"+Thread.currentThread().getName());
                Log.i("RxJavaThreadControl","value 的值"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
