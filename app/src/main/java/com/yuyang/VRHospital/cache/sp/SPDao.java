package com.yuyang.VRHospital.cache.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.yuyang.VRHospital.BaseApp;

/**
 * SharedPreference操作工具
 */
public class SPDao {
    private static String DEFUND_FILE = BaseApp.getAppInfo().name;
    /**
     * 保存值到指定SharedPreference文件
     */
    public static void saveSharedPreferences(String key, boolean value){
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(DEFUND_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public static void saveSharedPreferences(String fileName, String key,
                                             boolean value) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(fileName,
                          Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 从指定SharedPreference文件获取值
     *
     */
    public static boolean getSharedPreferences(String key, boolean defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                    DEFUND_FILE, Context.MODE_PRIVATE);
            return sp.getBoolean(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }
    public static boolean getSharedPreferences(String fileName, String key,
                                               boolean defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                              fileName, Context.MODE_PRIVATE);
            return sp.getBoolean(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * 保存值到指定SharedPreference文件
     */
    public static void saveSharedPreferences(String key, float value) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(DEFUND_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    public static void saveSharedPreferences(String fileName, String key,
                                             float value) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(fileName,
                          Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 从指定SharedPreference文件获取值
     */
    public static float getSharedPreferences(String key, float defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                    DEFUND_FILE, Context.MODE_PRIVATE);
            return sp.getFloat(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }
    public static float getSharedPreferences(String fileName, String key,
                                             float defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                              fileName, Context.MODE_PRIVATE);
            return sp.getFloat(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * 保存值到指定SharedPreference文件
     */
    public static void saveSharedPreferences(String key, int value) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(DEFUND_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public static void saveSharedPreferences(String fileName, String key,
                                             int value) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(fileName,
                          Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 从指定SharedPreference文件获取值
     */
    public static int getSharedPreferences(String key, int defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                    DEFUND_FILE, Context.MODE_PRIVATE);
            return sp.getInt(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }
    public static int getSharedPreferences(String fileName, String key,
                                           int defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                              fileName, Context.MODE_PRIVATE);
            return sp.getInt(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * 保存值到指定SharedPreference文件
     */
    public static void saveSharedPreferences(String key, long value) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(DEFUND_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public static void saveSharedPreferences(String fileName, String key,
                                             long value) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(fileName,
                          Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 从指定SharedPreference文件获取值
     *
     */
    public static long getSharedPreferences(String key, long defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                    DEFUND_FILE, Context.MODE_PRIVATE);
            return sp.getLong(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }
    public static long getSharedPreferences(String fileName, String key,
                                            long defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                              fileName, Context.MODE_PRIVATE);
            return sp.getLong(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * 保存值到指定SharedPreference文件
     */
    public static void saveSharedPreferences(String key, String value) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(DEFUND_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static void saveSharedPreferences(String fileName, String key,
                                             String value) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(fileName,
                          Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 从指定SharedPreference文件获取值
     *
     */
    public static String getSharedPreferences(String key, String defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                    DEFUND_FILE, Context.MODE_PRIVATE);
            return sp.getString(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }
    public static String getSharedPreferences(String fileName, String key,
                                              String defValue) {
        try {
            SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(
                              fileName, Context.MODE_PRIVATE);
            return sp.getString(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * 缓存信息
     * @param key	存入名字
     * @param obj	对象
     */
    public static void saveObject(String key,Object obj){
        SharedPreferences.Editor e = BaseApp.getAppContext().getSharedPreferences(DEFUND_FILE, Context.MODE_PRIVATE).edit();
        String json =  JSON.toJSONString(obj);
        e.putString(key, json);
        e.commit();
    }
    public static void saveObject(String fileName,String key,Object obj){
        SharedPreferences.Editor e = BaseApp.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        String json =  JSON.toJSONString(obj);
        e.putString(key, json);
        e.commit();
    }

    /**
     * 读取缓存信息
     * @param <T>
     * @param key	缓存名
     * @param classes	对象类型
     */
    public static <T> T getObject(String key,Class<T> classes){
        return getObject(DEFUND_FILE,key,classes,null);
    }
    public static <T> T getObject(String fileName, String key,Class<T> classes){
        return getObject(fileName,key,classes,null);
    }


    /**
     * 读取缓存信息
     * @param <T>
     * @param key	缓存名
     * @param classes	对象类型
     * @param defaultValue	默认参数
     */
    public static <T> T getObject(String key,Class<T> classes,T defaultValue){
        SharedPreferences s = BaseApp.getAppContext().getSharedPreferences(DEFUND_FILE, Context.MODE_PRIVATE);
        String jsonString = s.getString(key, "");
        if(!TextUtils.isEmpty(jsonString)){
            try {
                T o = JSON.parseObject(jsonString, classes);
                return o;
            }catch (Exception e){
                e.printStackTrace();
                return defaultValue;
            }
        }
        return defaultValue;
    }
    public static <T> T getObject(String fileName,String key,Class<T> classes,T defaultValue){
        SharedPreferences s = BaseApp.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String jsonString = s.getString(key, "");
        if(!TextUtils.isEmpty(jsonString)){
            try {
                T o = JSON.parseObject(jsonString, classes);
                return o;
            }catch (Exception e){
                e.printStackTrace();
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 清除某个sharedpreference内容
     */
    public static void clearSharedPreference() {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(DEFUND_FILE, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
    public static void clearSharedPreference(String fileName) {
        SharedPreferences sp = BaseApp.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

}
