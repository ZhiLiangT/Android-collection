package com.tzl.android_collection.common.ac;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tzl.android_collection.BaseActivity;
import com.tzl.android_collection.R;
import com.tzl.android_collection.common.adapter.ManyTaskDownAdapter;
import com.tzl.android_collection.common.bean.DownInfo;
import com.tzl.android_collection.main_utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ManyTaskDownActivity extends BaseActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private ManyTaskDownAdapter adapter;
    private List<DownInfo> dataList;
    private DBHelper dbHelper;
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_many_task_down);
    }

    @Override
    public void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.down_rv);
    }

    @Override
    public void initData() {
        dataList=new ArrayList<>();
        adapter=new ManyTaskDownAdapter(this,dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initEvent() {
        //获取数据库文件信息
        Observable.create(new ObservableOnSubscribe<List<DownInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<DownInfo>> e) throws Exception {
                    e.onNext(getDownList());
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<DownInfo>>() {
            @Override
            public void accept(List<DownInfo> downInfos) throws Exception {
                adapter.changeList(downInfos);
            }
        });

    }
    public List<DownInfo>  getDownList(){
        List<DownInfo> downInfosList=new ArrayList<>();
        dbHelper=DBHelper.getInstance(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM res_down ",null);
        while (cursor.moveToNext()){
            DownInfo downInfo=new DownInfo();
            int id=cursor.getInt(cursor.getColumnIndex("res_Id"));
            int state=cursor.getInt(cursor.getColumnIndex("res_state"));
            String url=cursor.getString(cursor.getColumnIndex("res_url"));
            String path=cursor.getString(cursor.getColumnIndex("res_path"));
            String name=cursor.getString(cursor.getColumnIndex("res_name"));
            int type=cursor.getInt(cursor.getColumnIndex("res_type"));
            int progress=cursor.getInt(cursor.getColumnIndex("res_progress"));
            int size=cursor.getInt(cursor.getColumnIndex("res_size"));
            downInfo.setResId(id);
            downInfo.setResName(name);
            downInfo.setResPath(path);
            downInfo.setResState(state);
            downInfo.setResType(type);
            downInfo.setResUrl(url);
            downInfo.setProgress(progress);
            downInfo.setResSize(size);
            downInfosList.add(downInfo);
        }
        return downInfosList;
    }

    @Override
    public void onClick(View v) {

    }

}
