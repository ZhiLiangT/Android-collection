package com.tzl.android_collection.common.down;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.tzl.android_collection.R;

public class DownTestActivity extends AppCompatActivity {
    private TextView textView;
    private Button btDown1;
    private String url="http://7xjww9.com1.z0.glb.clouddn.com/Hopetoun_falls.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_test);
        textView=(TextView) findViewById(R.id.down_tv);
        btDown1=(Button) findViewById(R.id.down_bt1);
        btDown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(DownTestActivity.this, "开始下载",Toast.LENGTH_SHORT).show();
                testCall();
            }
        });
    }
    public void test(String url){
        FileDownloader.getImpl().create(url).setWifiRequired(true).setPath("/storage/emulated/0/Test/Hopetoun_falls.jpg").setListener(new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }
            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                int percent=(int) ((double) soFarBytes / (double) totalBytes * 100);
                textView.setText("("+percent+"%"+")");
            }

            @Override
            protected void blockComplete(BaseDownloadTask task) {

            }

            @Override
            protected void completed(BaseDownloadTask task) {
                Toast.makeText(DownTestActivity.this,"下载完成!",Toast.LENGTH_SHORT).show();
                textView.setText("("+"100%"+")");
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
            test(url);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_FILE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                test(url);
            } else {
                // Permission Denied
                Toast.makeText(DownTestActivity.this, "授权失败，无法下载", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
