package com.yuyang.VRHospital.utils;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by yuyang on 16/4/22.
 */
public class DialogUtil {

    public static void setFullScreenWidth(Activity context, Dialog dialog) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);
            Window win = dialog.getWindow();
            WindowManager.LayoutParams params = win.getAttributes();
            params.width = dm.widthPixels;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            win.setAttributes(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
