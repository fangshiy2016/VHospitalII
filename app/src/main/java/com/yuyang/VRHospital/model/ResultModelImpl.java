package com.yuyang.VRHospital.model;

import com.yuyang.VRHospital.model.iModel.IResultModel;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.network.http.Http;
import com.yuyang.VRHospital.network.listener.BaseListener;

/**
 * Created by yuyang on 16/4/23.
 */
public class ResultModelImpl implements IResultModel {
    private BaseListener callBack;

    public ResultModelImpl(BaseListener callBack){
        this.callBack = callBack;
    }

    @Override
    public void loadResult(String tag) {
        //Map<String,String> params = new HashMap<>();
        String urlParam="?Code=" + tag;
        Http.get(Url.URL_RESULT_STEP + urlParam, callBack);
    }
}
