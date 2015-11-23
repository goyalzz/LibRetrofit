package com.goyalzz.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {

    private MyPreferences myPref;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final String MYPREF = "SamplePref";
    private final String USERID = "UserId";
    private final String USERNAME = "UserName";

    public MyPreferences getInstance(Context context) {
        if(myPref == null) {
            myPref = new MyPreferences(context);
        }
        return myPref;
    }

    private MyPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void clearAllPreferences() {
        editor.clear();
    }

    public void setUserId(int id) {
        editor.putInt(USERID, id);
        editor.apply();
    }

    public int getUserId() {
        return sharedPreferences.getInt(USERID, 0);
    }

    public void setUserName(String name) {
        editor.putString(USERNAME, name);
        editor.apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(USERNAME, "");
    }

}
