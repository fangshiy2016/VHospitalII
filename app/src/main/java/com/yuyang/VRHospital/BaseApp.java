package com.yuyang.VRHospital;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yuyang.VRHospital.utils.CrashHandler;

public class BaseApp extends Application {
    private static BaseApp sInstance;

    public static final BaseApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        //初始化图片加载
        Fresco.initialize(this);

        //往sd卡写入报错log
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.start(this);
    }

    /**
     * 获取Application Context
     *
     * @return
     */
    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    /**
     * 获取ApplicationInfo
     *
     * @return
     */
    public static ApplicationInfo getAppInfo() {
        return sInstance.getApplicationInfo();
    }

}
