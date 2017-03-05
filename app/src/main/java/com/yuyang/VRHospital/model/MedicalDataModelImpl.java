package com.yuyang.VRHospital.model;

import com.alibaba.fastjson.JSON;
import com.yuyang.VRHospital.bean.QueryZhishiContionBean;
import com.yuyang.VRHospital.model.iModel.IMedicalDataModel;
import com.yuyang.VRHospital.network.ParamsConstants;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.network.http.Http;
import com.yuyang.VRHospital.network.listener.BaseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuyang on 16/4/22.
 */
public class MedicalDataModelImpl implements IMedicalDataModel {

    @Override
    public void loadMedical(QueryZhishiContionBean conditionBean, BaseListener callBack) {

        String  jsonCondition = JSON.toJSONString(conditionBean);
        Http.postJson(Url.URL_ZHISHI_QUERY, jsonCondition/*"{\"pageIndex\":1,\"pageSize\":10}"*/, callBack);
    }

    @Override
    public void loadMedicalByFolderID(int FolderID, BaseListener callBack)
    {

        String urlParam="?FolderID=" + FolderID;
        Http.get(Url.URL_ZHISHI_BYFOLDERID + urlParam, callBack);
    }
}
