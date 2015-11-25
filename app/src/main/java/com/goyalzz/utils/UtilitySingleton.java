package com.goyalzz.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

/**
 * Created by navjot on 14/1/15.
 */
public class UtilitySingleton {

    private static final UtilitySingleton ourInstance = new UtilitySingleton();
    private static Context context;

    private UtilitySingleton() {

    }

    public static UtilitySingleton getInstance(Context context) {
        UtilitySingleton.context = context;
        return ourInstance;
    }

    public String GetText(String text) {
        if ((text == null) || text.trim().equalsIgnoreCase("null") || text.trim().length() == 0 ) {
            return "";
        } else {
            return text;
        }
    }

    /**
     * Description : Check if user is online or not
     * @return
     */
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        ShowToast("No Network Found");
        return false;
    }

    /**
     * Description : Toast with Message String as input
     */
    public void ShowToast(String msg) {
        if (msg != null && !msg.trim().equalsIgnoreCase("")) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Description : Validates the email
     * @param editText
     * @return true : if email is valid
     */
    public boolean validateEmailwithSnackBar(EditText editText, View view)
    {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(editText.getText().toString());
        if (matcher.matches())
        {
            return true;
        }
        else
        {
            ShowSnackBar("Invalid EmailId", view);
            return false;
        }
    }

    public void ShowSnackBar(String text, View view) {
        if(GetText(text).length()>0) {
            Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * Description : Toast with resourceID as input
     */
    public void ShowToast(int msgID) {
        ShowToast(context.getString(msgID));
    }

    public static void setListViewHeightBasedOnChildren(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
        {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
            {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /**
     * Description : Hide the soft keyboard
     * @param view : Pass the current view
     */
    public void hideSoftKeyboard(View view)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Description : Validates the email
     * @param editText
     * @return true : if email is valid
     */
    public boolean validateEmail(EditText editText)
    {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(editText.getText().toString());
        if (matcher.matches())
        {
            return true;
        }
        else
        {
            ShowToast("Invalid EmailId");
            return false;
        }
    }

    /**
     * Description : Set click listeners for Activity
     */
    public void SetClickListener(View.OnClickListener THIS, int[] clickIDs, View view)
    {
            for (int id : clickIDs)
            {
                try
                {
                    view.findViewById(id).setOnClickListener(THIS);
                }
                catch (NullPointerException e)
                {
                    e.printStackTrace();
                    continue;
                }
            }
    }

    /**
     * Description : Get typed input from string model
     * @param model
     * @return
     */
    public TypedInput getTypedInput(String model)
    {
        try
        {
            return new TypedByteArray("application/json", model.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
