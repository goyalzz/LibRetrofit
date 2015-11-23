package com.goyalzz.core;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.goyalzz.network.RetrofitApi;

/**
 * Created by ankush on 23/11/15.
 */
public class BaseActivity extends AppCompatActivity {

    public RetrofitApi getRestService() {
        return ((BaseApplication) getApplication()).getRestServiceInstance();
    }

}
