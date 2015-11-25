package com.goyalzz.utils;

/**
 *created by Balwinder Singh on 8-07-15
 */

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.goyalzz.R;
import com.goyalzz.view.TransparentProgressDialog;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class CustomCallBacks<T> implements Callback<T>
{
    Context context;
    private TransparentProgressDialog dialog;
    public CustomCallBacks(Context context, boolean showProgress)
    {
        this.context = context;
        if (showProgress)
        {
            setDialog();
        }
    }
    @Override
    public void failure(RetrofitError arg0)
    {
        stopDialog();
        if(arg0.getKind() == RetrofitError.Kind.NETWORK) {
            Toast.makeText(context, "Network Error! Unable to complete request", Toast.LENGTH_LONG).show();
        }
        else if(arg0.getKind() == RetrofitError.Kind.CONVERSION) {
            Toast.makeText(context, "Conversion Error, Please Verify the Response Model and Response Structure", Toast.LENGTH_LONG).show();
        }
        else {
            arg0.printStackTrace();
            this.onFailure(arg0);
        }
    }
    @Override
    public void success(T arg0, Response arg1)
    {
        stopDialog();
        this.onSucess(arg0, arg1);
        String json_str = new Gson().toJson(arg0);
        System.out.println("testjson="+json_str);

    }
    public abstract void onSucess(T arg0, Response arg1);
    public abstract void onFailure(RetrofitError arg0);
    private void setDialog()
    {
        dialog = new TransparentProgressDialog(context, R.drawable.logo);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setContentView(R.layout.view_progress_dialog);
    }
    void stopDialog()
    {
        if (dialog != null && dialog.isShowing())
        {
            dialog.dismiss();
        }
    }

}