package com.yuyang.VRHospital.model;

import com.yuyang.VRHospital.model.iModel.ICaseDbModel;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.network.http.Http;
import com.yuyang.VRHospital.network.listener.BaseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuyang on 16/4/21.
 */
public class CaseDbModelImpl implements ICaseDbModel {
    private BaseListener listener;

    public CaseDbModelImpl(BaseListener listener){
        this.listener = listener;
    }

    @Override
    public void loadCaseData(String condition, int page, int pageSize) {
        Map<String,String> params = new HashMap<>();
        //TODO yuyang 请自行处理
        String urlParam="?";
        if(condition != null && condition != "") {
            urlParam += "userCode=" + condition;
        }
        if(page > 0){
            if(urlParam != "?") {
                urlParam = urlParam + "&&" ;
            }
            urlParam = urlParam + "PageIndex=" + page;
        }
        if(pageSize > 0){
            if(urlParam != "?") {
                urlParam += "&&" ;
            }
            urlParam += "pageSize=" + pageSize;
        }

        Http.get(Url.URL_CASE_QUERY + urlParam, listener);
    }
}
