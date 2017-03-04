package com.yuyang.VRHospital.model;

import com.yuyang.VRHospital.model.iModel.ICacheModel;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.network.http.Http;
import com.yuyang.VRHospital.network.listener.CallbackListener;

/**
 * Created by yuyang on 16/4/23.
 */
public class CacheModelImpl implements ICacheModel {
    private CallbackListener callBack;

    public CacheModelImpl(CallbackListener callBack){
        this.callBack = callBack;
    }

    @Override
    public void downloadImage(String imageUrl, String localPath) {


        Http.download(Url.IMAGE_URI_RELEASE + imageUrl, localPath, callBack);
    }
}
