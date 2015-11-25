package com.goyalzz.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.AppBarLayout;
import android.util.Log;

/**
 * Description : Used by picasso to make round corner image
 */
public class BlurTransformation implements com.squareup.picasso.Transformation
{
    private int radius;
    private Context context;
    private AppBarLayout appbar;

    public BlurTransformation(Context context, int radius, AppBarLayout appbar)
    {
        this.radius = radius;
        this.context = context;
        this.appbar = appbar;
    }

    @Override
    public Bitmap transform(final Bitmap source)
    {

        Bitmap scaled = BitmapFunctions.scaleCenterCrop(source, appbar.getMeasuredHeight(), appbar.getMeasuredWidth());
//        Bitmap scaled = source;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                Log.d("Blur", "Render");
                RenderScript rs = RenderScript.create(context);
                Allocation overlayAlloc = Allocation.createFromBitmap(rs, scaled);
                ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());
                blur.setInput(overlayAlloc);
                blur.setRadius(Float.parseFloat(String.valueOf(radius)));
                blur.forEach(overlayAlloc);
                overlayAlloc.copyTo(scaled);
            } else {

                scaled = BitmapFunctions.doBlur(scaled, radius, true);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return scaled;
//        return new BitmapSplashDrawable(getResources(), scaled);
    }

    @Override
    public String key() {
        return "blur";
    }
}