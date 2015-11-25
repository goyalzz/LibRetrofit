package com.goyalzz.core;

import android.app.Application;

import com.goyalzz.helper.Config;
import com.goyalzz.network.RetrofitApi;
import com.goyalzz.utils.MyUrlConnectionClient;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by ankush on 23/11/15.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RetrofitApi getRestServiceInstance() {
//        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
//                .setClient(new MyUrlConnectionClient()).build();
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Config.API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient())).build();
        return restAdapter.create(RetrofitApi.class);
    }

}
