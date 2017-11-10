package com.tzl.android_collection.external_lib.rxjava;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaOperatorActivity extends BaseActivity implements View.OnClickListener{
    private Button bt1,bt2,bt3,bt4;
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_rx_java_operator);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.rx_operator_map);
        bt2= (Button) findViewById(R.id.rx_operator_flatmap);
        bt3= (Button) findViewById(R.id.rx_operator_conncatmap);
        bt4= (Button) findViewById(R.id.rx_operator_zip);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rx_operator_map:
                mapTest2();
                break;
            case R.id.rx_operator_flatmap:
                flatMapTest1();
                break;
            case R.id.rx_operator_conncatmap:
                concaMapTest();
                break;
            case R.id.rx_operator_zip:
                zipTest();
                break;
        }
    }

    /**
     * Zip通过一个函数将多个Observable发送的事件结合到一起，
     * 然后发送这些组合到一起的事件. 它按照严格的顺序应用这个函数。
     * 它只发射与发射数据项最少的那个Observable一样多的数据
     */
    private void zipTest() {
        Observable<Integer> observable1=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.i("RxJavaOperatorActivity","observable1--1");
                e.onNext(1);
                Log.i("RxJavaOperatorActivity","observable1--2");
                e.onNext(2);
                Log.i("RxJavaOperatorActivity","observable1--3");
                e.onNext(3);
                Log.i("RxJavaOperatorActivity","observable1--4");
                e.onNext(4);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable<String> observable2=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i("RxJavaOperatorActivity","observable2--A");
                e.onNext("A");
                Log.i("RxJavaOperatorActivity","observable2--B");
                e.onNext("B");
                Log.i("RxJavaOperatorActivity","observable2--C");
                e.onNext("C");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer+s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("RxJavaOperatorActivity","zip:"+s);
            }
        });

    }

    public void mapTest1(){
        Observable<Integer> observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        });
        Observer<Integer> observer=new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.i("RxJavaOperatorActivity","map--"+value);
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
    public void mapTest2(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is "+integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("RxJavaOperatorActivity","map---"+s);
            }
        });
    }
    public void flatMapTest1(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list=new ArrayList<String>();
                for (int i=0;i<3;i++){
                    list.add("I am value "+integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("RxJavaOperatorActivity",s);
            }
        });
    }
    public void concaMapTest(){

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list=new ArrayList<String>();
                for (int i=0;i<3;i++){
                    list.add("this is "+integer);
                }
                return Observable.fromIterable(list).delay(10,TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                    Log.i("RxJavaOperatorActivity",s);
            }
        });
    }



}
