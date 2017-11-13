package com.tzl.android_collection.external_lib.rxjava;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.external_lib.rxjava.utils.DataUtils;
import com.tzl.android_collection.main_utils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ExamplePracticeActivity extends BaseActivity implements View.OnClickListener{
    private TextView tvContent;
    private ImageView ivImg;
    private Button bt1,bt2,bt3,bt4;
    private String[] imgUrlList;
    private StringBuilder builder;
    private String host="https:";

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_example_practice);
    }

    @Override
    public void initView() {
        tvContent= (TextView) findViewById(R.id.rx_example_tv);
        ivImg= (ImageView) findViewById(R.id.rx_example_iv);
        bt1= (Button) findViewById(R.id.rx_example_bt1);
        bt2= (Button) findViewById(R.id.rx_example_bt2);
        bt3= (Button) findViewById(R.id.rx_example_bt3);
        bt4= (Button) findViewById(R.id.rx_example_bt4);
    }

    @Override
    public void initData() {
        imgUrlList= DataUtils.imgUrls;
        builder=new StringBuilder();
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
            case R.id.rx_example_bt1:
                test1();
                break;
            case R.id.rx_example_bt2:
                test2();
                break;
            case R.id.rx_example_bt3:
                test3();
                break;
            case R.id.rx_example_bt4:
                test4();
                break;
        }
    }

    /**
     * 练习map操作符，将图片url集合，依次获取到相应的bitmap，显示在imageview上
     */
    public void test1(){
        Observable.fromArray(imgUrlList)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        return BitmapUtils.getHttpBitmap(s);
                    }
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        ivImg.setImageBitmap(bitmap);
                    }
                });
    }

    /**
     * 练习map操作符，字符串重新拼接
     */
    public void test2(){

        Observable.just("我是第一句","我是第二句","我是第三句")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s+"ABCD";
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        builder.append(value);
                        tvContent.setText(builder.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 练习map操作符，多个map，一个map处理url拼接，一个将拼接好的url进行网络访问
     */
    public void test3(){
        Observable.fromArray(DataUtils.imgUrlsTest1)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return host+s;
                    }
                })
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        return BitmapUtils.getHttpBitmap(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap value) {
                        ivImg.setImageBitmap(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 练习flatMap操作符，解决嵌套回调
     */
    public void test4(){
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
                    list.add("this is "+integer);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.i("ExamplePracticeActivity","flat -- value --"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
