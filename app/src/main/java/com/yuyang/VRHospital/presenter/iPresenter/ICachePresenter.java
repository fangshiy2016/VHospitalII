package com.yuyang.VRHospital.presenter.iPresenter;

/**
 * Created by yuyang on 16/4/21.
 */
public interface ICachePresenter {
    void downloadImage(String imageUrl, String localPath);

    void activateAccount(String userCode, String tel, String deviceNumber);
}
