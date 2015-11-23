package com.goyalzz.network;

import com.goyalzz.helper.Config;

import retrofit.http.Field;
import retrofit.http.POST;

/**
 * Created by ankush on 23/11/15.
 */
public interface RetrofitApi {

    @POST(Config.SUBMITDETAILSAPI)
    void postUserDetails(@Field("KEY") String name);

}
