package com.yuyang.VRHospital.model.iModel;

import com.yuyang.VRHospital.network.listener.BaseListener;

/**
 * Created by fanshy on 2016/7/7.
 */
public interface IHomeLogicModel {
    void activateAccount(String userCode, String tel, String deviceNumber, BaseListener callBack);
}
