package com.yuyang.VRHospital.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;

import com.yuyang.VRHospital.BaseApp;

import java.util.List;

/**
 * Created by JESSY on 15/8/25.
 */
public class AppUtils {

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public static boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) BaseApp.getAppContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        String packageName = BaseApp.getAppContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果为true，则表示屏幕“亮”了，否则屏幕“暗”了。
     * @return
     */
    public static boolean isPowerScreenOn(){
        PowerManager pm = (PowerManager) BaseApp.getAppContext().getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }

    /**
     * 得到本机Mac地址
     *
     * @return
     */
    public static String getLocalMac(Context context) {
        String mac = "";
        // 获取wifi管理器
        WifiManager wifiMng = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfor = wifiMng.getConnectionInfo();
        mac = wifiInfor.getMacAddress();
        return mac;
    }

    /**
     * 获取应用的版本信息
     * @return
     */
    public static String getAppVersion(){
        try {
            return BaseApp.getInstance().getPackageManager().getPackageInfo(
                    BaseApp.getInstance().getPackageName(), 0).versionName;
        } catch (Exception e) {}
        return "";
    }

    /**
     * 获取本地语言
     * @return
     */
    public static String getLocalLanguage(){
        try {
            return BaseApp.getAppContext().getResources().getConfiguration().locale.getLanguage();
        } catch (Exception e) {}
        return "";
    }

}
