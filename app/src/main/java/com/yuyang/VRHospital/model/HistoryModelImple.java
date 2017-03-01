package com.yuyang.VRHospital.model;

import com.yuyang.VRHospital.model.iModel.IHistoryModel;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.network.http.Http;
import com.yuyang.VRHospital.network.listener.BaseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuyang on 2016/4/24.
 */
public class HistoryModelImple implements IHistoryModel {
    @Override
    public void loadHistoryContent(String tag, BaseListener callBack) {
        Map<String,String> params = new HashMap<>();
        Http.post(Url.BASE_URI_RELEASE,params,callBack);
    }
}
