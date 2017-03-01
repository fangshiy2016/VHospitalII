package com.yuyang.VRHospital.model.iModel;

import com.yuyang.VRHospital.network.listener.BaseListener;

/**
 * Created by yuyang on 2016/4/24.
 */
public interface IHistoryModel {
    void loadHistoryContent(String tag, BaseListener callBack);
}
