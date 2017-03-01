package com.yuyang.VRHospital.network.http;

import android.support.annotation.Nullable;

import com.yuyang.VRHospital.network.listener.BaseListener;
import com.yuyang.VRHospital.network.listener.CallbackListener;

import java.util.Map;

public class Http extends BaseHttp{
    /**
     * get 请求 不带tag
     * @param url
     * @param listener
     */
    public static void get(String url,BaseListener<?> listener)
    {
        getInstance().baseGet(url, listener, url);
    }

    /**
     * get 请求 带tag
     * @param url
     * @param listener
     */
    public static void get(String url,BaseListener<?> listener,@Nullable String tag)
    {
        getInstance().baseGet(url, listener, tag);
    }

    /**
     * post 请求
     * @param url
     * @param params
     * @param listener
     */
    public static void post(String url,Map<String,String> params,BaseListener<?> listener)
    {
        getInstance().basePost(url, params, listener);
    }

    public static void postJson(String url, String json, BaseListener<?> listener)
    {
        getInstance().basePostJson(url, json, listener);
    }
    /**
     * 无参post 请求
     * @param url
     * @param listener
     */
    public static void postNotParams(String url,CallbackListener<?> listener)
    {
        getInstance().basePost(url, null, listener);
    }

    /**
     * 下载
     * @param url 下载的url
     * @param savePath 保存的路径
     * @param listener 回调
     */
    public static void download(String url,String savePath,CallbackListener<?> listener)
    {
        getInstance().baseDownload(url, savePath, listener);
    }

    /**
     * 取消request
     */
    public static void cancelRequest(String url)
    {
        getInstance().cancel(url);
    }


}
