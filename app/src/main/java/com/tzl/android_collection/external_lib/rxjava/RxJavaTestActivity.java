package com.tzl.android_collection.external_lib.rxjava;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTestActivity extends BaseActivity implements View.OnClickListener{

    private Button btEasy1;
    private Button btEasy2;
    private Button btEasy3;
    private Button btEasy4;
    private Button btEasy5;
    private Button btEasy6;
    private Button btEasy7;
    private Button btEasy8;
    private Button btEasy9;
    private Button btEasy10;
    private Button btEasy11;
    private Button btEasy12;
    private Button btEasy13;
    private Button btEasy14;
    private Button btEasy15;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_rx_java_test);
    }

    @Override
    public void initView() {
        btEasy1= (Button) findViewById(R.id.rxjava_easy_1);
        btEasy2= (Button) findViewById(R.id.rxjava_easy_2);
        btEasy3= (Button) findViewById(R.id.rxjava_easy_3);
        btEasy4= (Button) findViewById(R.id.rxjava_easy_4);
        btEasy5= (Button) findViewById(R.id.rxjava_easy_5);
        btEasy6= (Button) findViewById(R.id.rxjava_easy_6);
        btEasy7= (Button) findViewById(R.id.rxjava_easy_7);
        btEasy8= (Button) findViewById(R.id.rxjava_easy_8);
        btEasy9= (Button) findViewById(R.id.rxjava_easy_9);
        btEasy10= (Button) findViewById(R.id.rxjava_easy_10);
        btEasy11= (Button) findViewById(R.id.rxjava_easy_11);
        btEasy12= (Button) findViewById(R.id.rxjava_easy_12);
        btEasy13= (Button) findViewById(R.id.rxjava_easy_13);
        btEasy14= (Button) findViewById(R.id.rxjava_easy_14);
        btEasy15= (Button) findViewById(R.id.rxjava_easy_15);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        btEasy1.setOnClickListener(this);
        btEasy2.setOnClickListener(this);
        btEasy3.setOnClickListener(this);
        btEasy4.setOnClickListener(this);
        btEasy5.setOnClickListener(this);
        btEasy6.setOnClickListener(this);
        btEasy7.setOnClickListener(this);
        btEasy8.setOnClickListener(this);
        btEasy9.setOnClickListener(this);
        btEasy10.setOnClickListener(this);
        btEasy11.setOnClickListener(this);
        btEasy12.setOnClickListener(this);
        btEasy13.setOnClickListener(this);
        btEasy14.setOnClickListener(this);
        btEasy15.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rxjava_easy_1:
                Test1();
                break;
            case R.id.rxjava_easy_2:
                Test2();
                break;
            case R.id.rxjava_easy_3:
                Test3();
                break;
            case R.id.rxjava_easy_4:
                Test4();
                break;
            case R.id.rxjava_easy_5:
                Test5();
                break;
            case R.id.rxjava_easy_6:
                Test6();
                break;
            case R.id.rxjava_easy_7:
                Test7();
                break;
            case R.id.rxjava_easy_8:
                Test8();
                break;
            case R.id.rxjava_easy_9:
                Test9();
                break;
            case R.id.rxjava_easy_10:
                Intent intent=new Intent(this,RxJavaOperatorActivity.class);
                startActivity(intent);
                break;
            case R.id.rxjava_easy_11:
                Intent intentThread=new Intent(this,RxJavaThreadControlActivity.class);
                startActivity(intentThread);
                break;
            case R.id.rxjava_easy_12:
                backPressureTest();
                break;
            case R.id.rxjava_easy_13:
                backPressureTest2();
                break;
            case R.id.rxjava_easy_14:
                Intent intetnFlowable=new Intent(this,FlowableTestActivity.class);
                startActivity(intetnFlowable);
                break;
            case R.id.rxjava_easy_15:
                Intent intetnExample=new Intent(this,ExamplePracticeActivity.class);
                startActivity(intetnExample);
                break;
        }
    }

    /**
     * 背压处理
     */
    private void backPressureTest() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i=0;;i++){
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("RxJavaTestActivity",""+integer);
            }
        });
    }

    /**
     * 使用filter过滤，只有是10的倍数 事件才能通过
     */
    private void backPressureTest2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i=0;;i++){
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io()).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {

                return integer%10==0;
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("RxJavaTestActivity",""+integer);
            }
         });
    }
    /**
     * 使用sample过滤，每隔2秒取一个事件给下游
     */
    private void backPressureTest3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i=0;;i++){
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .sample(2,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i("RxJavaTestActivity",""+integer);
                    }
                });
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
                Log.i("RxJavaTestActivity","value : "+value);
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
    //简单练习2  使用just创建Observable
    public void Test2(){
        Observable<String> observable=Observable.just("just1","just2","just3");
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.i("RxJavaTestActivity","just--"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    //简单练习3 使用from，遍历集合发送每个item
    public void Test3(){
        String[] strs=new String[]{"from1","from2","from3"};
        List<String> list=new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        Observable<String> observable=Observable.fromArray(strs);
        Observable<String> observable1=Observable.fromIterable(list);
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.i("RxJavaTestActivity","from--"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }
    //使用defer，有观察者订阅时才创建Observable，针对每个观察者创建一个新的Observable
    public void Test4(){
        Observable<String> deferObservable=Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {

                return Observable.just("defer1");
            }
        });
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.i("RxJavaTestActivity","defer--"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        deferObservable.subscribe(observer);
    }
    //使用interval，创建一个固定时间间隔发射整数序列的Obervable，可用做定时器
    public void Test5(){
        //每个一秒发送一次
        Observable<Long> intervalObservable=Observable.interval(1, TimeUnit.SECONDS);
        Observer<Long> observer=new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {
                Log.i("RxJavaTestActivity","interval"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        intervalObservable.subscribe(observer);
    }
    //使用range()创建一个发送特定整数序列的Observable，可用做定时器
    public void Test6(){
        //第一个参数为起始值，第二个参数为发射的个数，如果为0则不发送，为负数则抛异常
        Observable<Integer> rangeObservable=Observable.range(12,16);
        Observer<Integer> observer=new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.i("RxJavaTestActivity","range--"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        rangeObservable.subscribe(observer);
    }
    //使用timer(),创建一个给定延时后发射的一个值，等同于Android中的Handler的postDelay()方法
    public void Test7(){
        //3秒后发射一个值
        Observable<Long> timerObservable=Observable.timer(3,TimeUnit.SECONDS);
        Observer<Long> observer=new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {
                Log.i("RxJavaTestActivity","timer--"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        timerObservable.subscribe(observer);
    }
    //使用repeat()创建一个重复发射特定数据的Observable
    public void Test8(){
        Observable<String> observable=Observable.just("repeat HAHA").repeat(3);
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.i("RxJavaTestActivity","repeat--"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Test9(){
        Observable<String> observable=Observable.just("最简明1","最简明2","最简明3");
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("RxJavaTestActivity","最简明--"+s);
            }
        });
    }


}
