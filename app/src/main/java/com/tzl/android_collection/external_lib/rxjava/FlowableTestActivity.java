package com.tzl.android_collection.external_lib.rxjava;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FlowableTestActivity extends BaseActivity implements View.OnClickListener{

    private Button bt1,bt2,bt3,bt4,bt5;
    public Subscription mSubscription;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_flowable_test);
    }

    @Override
    public void initView() {
        bt1= (Button) findViewById(R.id.flow_1);
        bt2= (Button) findViewById(R.id.flow_2);
        bt3= (Button) findViewById(R.id.flow_3);
        bt4= (Button) findViewById(R.id.flow_4);
        bt5= (Button) findViewById(R.id.flow_5);
    }

    @Override
    public void initData() {
        test2();
    }

    @Override
    public void initEvent() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.flow_1:
                test1();
                break;
            case R.id.flow_2:
                request(1);
                break;
            case R.id.flow_3:
                test3();
                break;
            case R.id.flow_4:
                test4();
                break;
            case R.id.flow_5:
                test5();
                break;
        }
    }

    private void test1() {
        Flowable<Integer> flowable=Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                Log.i("FlowableTestActivity","subscribe--1");
                e.onNext(1);
                Log.i("FlowableTestActivity","subscribe--2");
                e.onNext(2);
                Log.i("FlowableTestActivity","subscribe--3");
                e.onNext(3);
                Log.i("FlowableTestActivity","subscribe--onComplete");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR);
        Subscriber<Integer> downstream=new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.i("FlowableTestActivity","onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("FlowableTestActivity","onNext--"+integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.i("FlowableTestActivity","onError");
            }

            @Override
            public void onComplete() {
                Log.i("FlowableTestActivity","onComplete");
            }
        };
        flowable.subscribe(downstream);
    }

    public  void request(long n) {
        mSubscription.request(n); //在外部调用request请求上游
    }

    /**
     * 上下游没有工作在同一线程内
     */
    public void test2(){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                Log.i("FlowableTestActivity","test2-emit--1");
                e.onNext(1);
                Log.i("FlowableTestActivity","test2-emit--2");
                e.onNext(2);
                Log.i("FlowableTestActivity","test2-emit--3");
                e.onNext(3);
                Log.i("FlowableTestActivity","test2-emit--complete");
                e.onComplete();
            }
        },BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.i("FlowableTestActivity","onSubscribe");
                        mSubscription=s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("FlowableTestActivity","onNext--"+integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.i("FlowableTestActivity","onError--");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("FlowableTestActivity","onComplete--");
                    }
                });
    }

    /**
     *  不限数量上游请求队列
     */
    public void test3(){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 1000; i++) {
                    Log.d("FlowableTestActivity", "emit " + i);
                    e.onNext(i);
                }
            }
        },BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d("FlowableTestActivity", "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("FlowableTestActivity", "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("FlowableTestActivity", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("FlowableTestActivity", "onComplete");
                    }
                });
    }

    /**
     * 在上游获取下游的请求数量
     */
    public Subscription subscription;
    public void test4(){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                Log.i("FlowableTestActivity","请求数量--"+e.requested());
            }
        },BackpressureStrategy.ERROR).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d("FlowableTestActivity", "onSubscribe");
                subscription=s;
                s.request(10);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("FlowableTestActivity", "onNext-"+integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.d("FlowableTestActivity", "onError");
            }

            @Override
            public void onComplete() {
                Log.d("FlowableTestActivity", "onComplete");
            }
        });
    }

    /**
     * 异步获取下游能处理事件的数量
     */
    public Subscription subscriptios;
    public void test5(){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                Log.i("FlowableTestActivity","请求数量--"+e.requested());
            }
        },BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d("FlowableTestActivity", "onSubscribe");
                        subscriptios=s;
                        s.request(1000);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("FlowableTestActivity", "onNext--"+integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("FlowableTestActivity", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("FlowableTestActivity", "onComplete");
                    }
                });
    }

}
