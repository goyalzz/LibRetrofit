package com.goyalzz.network;

import com.goyalzz.helper.Config;
import com.goyalzz.model.ResponseModel;
import com.goyalzz.utils.CustomCallBacks;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by ankush on 23/11/15.
 */
public interface RetrofitApi {

    @FormUrlEncoded
    @POST(Config.SUBMITDETAILSAPI)
    void postUserDetails(@Field("name") String name, @Field("email") String email,CustomCallBacks<ResponseModel> callBack);

}
