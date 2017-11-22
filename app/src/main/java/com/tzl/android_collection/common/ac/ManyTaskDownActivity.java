package com.tzl.android_collection.common.ac;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
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
    private static final String TAG = "ManyTaskDownActivity";
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
        testCall();
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
                dataList.clear();
                dataList.addAll(downInfos);
                adapter.notifyDataSetChanged();
                //adapter.changeList(downInfos);
            }
        });
        adapter.setOnItemClickListener(new ManyTaskDownAdapter.OnItemClickListener() {
            @Override
            public void onItemDownClick(DownInfo downInfo, SeekBar seekBar, TextView tvTotal, TextView tvCurr,int pos) {
                downList(downInfo,pos);
            }

            @Override
            public void onItemPause() {

            }

            @Override
            public void onItemDelete() {

            }

            @Override
            public void onItemCancel() {

            }

            @Override
            public void onItemReStart() {

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

    public void downList(DownInfo info, final int pos){
        Log.i(TAG, "downList: info:"+info.toString());
        String type;
        if (info.getResType()==0){
            type=".jpg";
        }else {
            type=".mp4";
        }
        FileDownloader.getImpl().create(info.getResUrl()).setWifiRequired(true).setPath("/storage/emulated/0/Test/Collection/Res/down/"+info.getResName()+type).setListener(new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }
            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                dataList.get(pos).setResState(1);
                dataList.get(pos).setResSize(totalBytes/1024/1024);
                dataList.get(pos).setProgress(soFarBytes/1024/1024);
                int percent=(int) ((double) soFarBytes / (double) totalBytes * 100);
                Log.i(TAG, "progress: "+percent);
            }

            @Override
            protected void blockComplete(BaseDownloadTask task) {

            }

            @Override
            protected void completed(BaseDownloadTask task) {
                dataList.get(pos).setResState(2);
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {

            }

            @Override
            protected void warn(BaseDownloadTask task) {
                //continueDownLoad(task);//如果存在了相同的任务，那么就继续下载
            }
        }).start();
    }



    private static final int MY_PERMISSIONS_REQUEST_WRITE_FILE = 1;
    //申请权限
    public void testCall()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_FILE);
        } else
        {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_FILE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

            } else {
                // Permission Denied
                Toast.makeText(ManyTaskDownActivity.this, "授权失败，无法下载", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
