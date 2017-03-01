package com.yuyang.VRHospital.model.iModel;

import com.yuyang.VRHospital.bean.QueryZhishiContionBean;
import com.yuyang.VRHospital.network.listener.BaseListener;

/**
 * Created by yuyang on 16/4/22.
 */
public interface IMedicalDataModel {

    void loadMedical(QueryZhishiContionBean conditionBean,  BaseListener callBack);
    void loadMedicalByFolderID(int FolderID, BaseListener callBack);
}
