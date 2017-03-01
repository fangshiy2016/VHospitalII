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

        //Map<String, String> params = new HashMap<>();

        //{"pageIndex":1,"pageSize":10,"order":"desc"}
        //if(current_page > 0) params.put(ParamsConstants.PAGE_INDEX, Integer.toString(current_page));
        //if(pageSize > 0) params.put(ParamsConstants.PAGE_SIZE, Integer.toString(pageSize));
        //if(order != null) params.put(ParamsConstants.ORDER_BY, order);
        //params = ParamsManage.addCommonParams(params);

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
