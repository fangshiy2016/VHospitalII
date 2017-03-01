package com.yuyang.VRHospital.network.http;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.yuyang.VRHospital.bean.ResultBean;
import com.yuyang.VRHospital.network.listener.BaseListener;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.network.util.GenericsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.yuyang.VRHospital.bean.ResultBean;
public class BaseHttp<T>{
    /** 超时时间10秒*/
    public static final long DEFAULT_MILLISECONDS = 10;

    private static final String TAG = "BaseFrame-BaseHttp";
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private static BaseHttp mBaseHttp;
    private Map<String, Call> allCall;
    public static final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");

    protected BaseHttp()
    {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS);
        mOkHttpClient = clientBuilder.build();
        mHandler = new Handler(Looper.getMainLooper());
        allCall = new HashMap<>();
    }

    protected static BaseHttp getInstance()
    {
        if(mBaseHttp == null)
        {
            mBaseHttp = new BaseHttp();
        }
        return  mBaseHttp;
    }

    protected OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    protected void baseGet(String url, BaseListener<T> listener, String tag)
    {
        Request request = getBaseRequest(url, tag);
        doRequest(url, request, listener);
    }

    protected void basePost(String url, Map<String, String> params, BaseListener<T> listener)
    {
        if (params == null) {
           baseGet(url,listener,null);return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        //Set<Map.Entry<String, String>> entrySet = params.entrySet();
        if (params != null) {
            for (String key : params.keySet()) {
                formBuilder.add(key, params.get(key));
            }
        }
        RequestBody requestBody = formBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .tag(url)
                .build();

        doRequest(url, request, listener);
    }

    protected void basePostJson(String url, String json, BaseListener<T> listener)
    {
        if (json == null) {
            baseGet(url,listener,null);return;
        }

        //json = "{\"pageIndex\":1,\"pageSize\":10}";

        RequestBody body = RequestBody.create(jsonType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        doRequest(url, request, listener);
    }


    protected void baseDownload(final String url, final String savePath, final CallbackListener<T> listener)
    {
        Request request = getBaseRequest(url, null);
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (isListenerNotNull(listener)) {
                    listener.onError(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();
                startDownload(url, savePath, is, listener);
            }

        });
        storeCall(url, call);
    }

    /**
     * 开始下载文件
     * @param urlPath
     * @param savePath
     * @param is
     * @param listener
     */
    private void startDownload(String urlPath,String savePath,InputStream is,CallbackListener<T> listener) {
        FileOutputStream fos = null;
        try {
            // 判断SD卡是否存在，并且是否具有读写权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                // 获得存储卡的路径
                // String sdpath = Environment.getExternalStorageDirectory().toString();
                System.out.println("savePath-------->" + savePath);
                File file = new File(savePath,getFileNameByUrl(urlPath));
                // 判断文件目录是否存在
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                // 缓存
                byte buf[] = new byte[1024*2];
                int len = 0;
                int progress = 0;
                while ((len = is.read(buf)) != -1)
                {
                    progress += len;
                    if(isListenerNotNull(listener)) listener.onDownloadProgress(progress);
                    fos.write(buf, 0, len);
                }
                if(isListenerNotNull(listener)) listener.onDownloadFinish(file.getAbsolutePath());
                fos.flush();
            } else {
                Log.i(TAG, "no find sdcard or sdcard no permission");
            }
        } catch (MalformedURLException e) {
            if(isListenerNotNull(listener))listener.onError(e);
        } catch (IOException e) {
            if(isListenerNotNull(listener))listener.onError(e);
        }finally
        {
            try
            {
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取Url名称
     * @param strUrl
     * @return
     */
    public static String getFileNameByUrl(String strUrl) {
        if (!TextUtils.isEmpty(strUrl)) {
            try {
                return strUrl.substring(strUrl.lastIndexOf("/") + 1, strUrl.length());
            } catch (IndexOutOfBoundsException e) {
                return strUrl;
            }
        } else {
            return strUrl;
        }
    }

    protected Request getBaseRequest(String url, String tag)
    {
        Request request = new Request.Builder().url(url).tag(tag).build();
        return  request;
    }

    protected void doRequest(String url, Request request, final BaseListener<T> listener)
    {
        if (isListenerNotNull(listener)){
            listener.onBefore();
        }
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (isListenerNotNull(listener)) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onError(e);
                            //处理在onBefore中的操作
                            listener.onAfter();
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.i(TAG, "结果：" + result);
                if (isListenerNotNull(listener)) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onStringResult(result);
                            try {
                                Class<T> clazz = GenericsUtils.getSuperClassGenricType(listener.getClass());
                                if (clazz == String.class) {        //字符串
                                    listener.onSuccess((T) result);
                                } else {//Object
                                    //修复下不规范的JSON字符串含有 null
                                    //String result2 = result.replaceAll("\"keShiModel\":null", "\"keShiModel\":");
                                    listener.onSuccess(JSON.parseObject(result, clazz));
                                }
                            } catch (Exception e) {
                                Log.i(TAG, "出错", e);
                                listener.onError(e);
                                //处理在onBefore中的操作
                                listener.onAfter();
                            }
                            //处理在onBefore中的操作
                            listener.onAfter();
                        }
                    });
                }
            }

        });
        storeCall(url, call);
    }

    public Boolean isListenerNotNull(BaseListener<T> listener)
    {
        if(listener!=null)
        {
            return true;
        }
        return false;
    }

    private void storeCall(String url, Call call){
        allCall.put(url,call);
    }

    public void cancel(String url){
        Call call = allCall.get(url);
        if(call != null){
            call.cancel();
        }
    }
}
