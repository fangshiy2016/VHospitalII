package com.yuyang.VRHospital.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

public class ImageLoaderUtils {

    public static void diaplayImage(Context context, String url, ImageView view, int defaultimage) {
        displayImage(context, url, view, defaultimage, new RequestListener<String, GlideDrawable>() {

            @Override
            public boolean onResourceReady(GlideDrawable arg0, String arg1, Target<GlideDrawable> arg2, boolean arg3, boolean arg4) {
                if (arg2 != null && arg0 != null) {
                    arg2.onResourceReady(arg0, null);
                }
                return false;
            }

            @Override
            public boolean onException(Exception arg0, String arg1, Target<GlideDrawable> arg2, boolean arg3) {
                return false;
            }
        });
    }

    public static void displayImage(Context context, String url, ImageView view, int defaultimage, RequestListener<String, GlideDrawable> listener) {
        Glide.with(context).load(url).error(defaultimage).placeholder(defaultimage).listener(listener).into(view);
    }

    public static String getHeadPicPath() {
        String cacheHeaderPath = Environment.getExternalStorageDirectory() + "/VHospital/HeaderImage";
        File f = new File(cacheHeaderPath);
        if (!f.exists()) {
            f.mkdir();
        }
       return cacheHeaderPath;
    }
}
