package com.yuyang.VRHospital.model;

import com.yuyang.VRHospital.model.iModel.IHomeLogicModel;
import com.yuyang.VRHospital.network.ParamsConstants;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.network.http.Http;
import com.yuyang.VRHospital.network.listener.BaseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshy on 2016/7/8.
 */
public class HomeLogicModelImpl implements IHomeLogicModel{

    @Override
    public  void activateAccount(String userCode, String tel, String deviceNumber, BaseListener callBack){

        Map<String,String> params = new HashMap<>();
        if(userCode != null) params.put("UserCode", userCode);
        //if(tel != null) params.put("Tel", tel);
        if(deviceNumber != null) params.put("DeviceNumber", deviceNumber);
        //接口设备号传了激活不起暂时屏蔽
        if(tel == null) params.put("DeviceNumber", deviceNumber);
            params = ParamsManage.addCommonParams(params);
        Http.post(Url.URL_ACTIVATE_USER, params, callBack);
    }
}
