package com.tzl.android_collection.main_utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tianzl on 2017/11/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    // 表名
    private static final String DB_NAME = "download.db";
    //版本号
    private static final int VERSION=2;
    // 建表语句
    private static final String SQL_CREATE="create table res_down(res_id integer primary key autoincrement," +
            "res_state integer,res_path text,res_url text,res_type integer,res_name text,res_progress integer)";

    public DBHelper(Context context) {
        super(context, Constant.DB_PATH, null, VERSION);
    }
    private static DBHelper dbHelper=null;

    public static DBHelper getInstance(Context context){
        //提高效率
        if(dbHelper == null)
        {
            //同步锁
            synchronized (DBHelper.class)
            {
                if(dbHelper == null)
                    dbHelper = new DBHelper(context);
            }
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建表
       // db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
