package com.yuyang.VRHospital.network.listener;

public abstract class BaseListener<T> {
    public abstract void onBefore();
    public abstract void onAfter();
    public abstract void onError(Exception e);
    public abstract void onSuccess(T result);
    public abstract void onStringResult(String result);
    public abstract void onDownloadFinish(String path);//下载完成
    public abstract void onDownloadProgress(int progress);//下载进度
}
