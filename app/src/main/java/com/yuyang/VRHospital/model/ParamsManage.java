package com.yuyang.VRHospital.model;

import com.yuyang.VRHospital.network.ParamsConstants;
import com.yuyang.VRHospital.utils.AppUtils;

import java.util.Map;

/**
 * Created by yuyang on 16/4/6.
 */
public class ParamsManage {
    /**
     * 添加统一请求参数。
     * @return
     */
    public static Map<String,String> addCommonParams(Map<String,String> params) {
        params.put(ParamsConstants.ACCOUNT, "");
        params.put(ParamsConstants.JSESSIONID, "");
        params.put(ParamsConstants.CHANNEL, "android");
        params.put(ParamsConstants.EDITION, AppUtils.getAppVersion());
        return params;
    }
}
