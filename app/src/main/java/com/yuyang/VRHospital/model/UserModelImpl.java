package com.yuyang.VRHospital.model;

import android.content.Context;

import com.yuyang.VRHospital.model.iModel.IUserModel;
import com.yuyang.VRHospital.network.ParamsConstants;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.network.http.Http;
import com.yuyang.VRHospital.network.listener.BaseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuyang on 16/4/5.
 */
public class UserModelImpl implements IUserModel {
    private Context context;
    private BaseListener callBack;

    public UserModelImpl(BaseListener callBack, Context context){
        this.context = context;
        this.callBack = callBack;
    }

    /**
     * 登陆接口
     * @param username
     * @param password
     * @param i TODO yuyang 测试使用
     * @param token TODO yuyang 测试使用
     */
    @Override
    public void login(String username, String password, int i, String deviceNumber) {

        Map<String,String> params = new HashMap<>();
        params.put(ParamsConstants.USER_NAME, username);
        params.put(ParamsConstants.PASSWORD, password);
        //params.put(ParamsConstants.KICKED, String.valueOf(i));
        params.put(ParamsConstants.DEVICENUMBER, deviceNumber);
        params = ParamsManage.addCommonParams(params);
        Http.post(Url.URL_USER_LOGIN, params, callBack);
    }
}
