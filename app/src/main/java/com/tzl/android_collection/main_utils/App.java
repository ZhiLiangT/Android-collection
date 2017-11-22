package com.tzl.android_collection.main_utils;

import android.app.Application;
import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;

public class App extends Application{
	public static Context CONTEXT;
	@Override
	public void onCreate() {
		super.onCreate();
		CONTEXT = this;
		FileDownloader.setupOnApplicationOnCreate(this).commit();

	}

}
