package com.yuyang.VRHospital.model;

import com.yuyang.VRHospital.model.iModel.IExplainModel;
import com.yuyang.VRHospital.network.ParamsConstants;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.network.http.Http;
import com.yuyang.VRHospital.network.listener.BaseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuyang on 16/4/20.
 */
public class ExplainModel implements IExplainModel {
    private BaseListener callBack;

    public ExplainModel(BaseListener callBack){
        this.callBack = callBack;
    }

    @Override
    public void loadExplainBean(String code) {
        //Map<String,String> params = new HashMap<>();
        //params.put(ParamsConstants.LABLE, lable);
        String urlParam="?Code=" + code;
        Http.get(Url.URL_ZHISHI_GET + urlParam, callBack);
    }
}
