package com.tzl.android_collection.external_lib.retrofit2.inter;

import com.tzl.android_collection.external_lib.retrofit2.bean.Test;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tianzl on 2017/11/13.
 */

public interface AppURL {

    @GET("api/data/Android/10/1")
    Call<ResponseBody> getAndroidInfo();

    @GET("users/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user")String user);

    @GET("users/{user}/repos")
    Call<Test> getTestInfo();
}
