package com.yuyang.VRHospital.model.iModel;

import com.yuyang.VRHospital.network.listener.BaseListener;

/**
 * Created by yuyang on 16/4/21.
 */
public interface ICacheModel {
    void downloadImage(String imageUrl, String localPath);

    void activateAccount(String userCode, String tel, String deviceNumber, BaseListener callBack);
}
