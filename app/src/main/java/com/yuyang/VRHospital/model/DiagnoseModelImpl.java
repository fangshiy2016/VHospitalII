package com.yuyang.VRHospital.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.yuyang.VRHospital.bean.CaseItemBean;
import com.yuyang.VRHospital.model.iModel.IDiagnoseModel;
import com.yuyang.VRHospital.network.ParamsConstants;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.network.http.Http;
import com.yuyang.VRHospital.network.listener.BaseListener;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuyang on 16/4/24.
 */
public class DiagnoseModelImpl implements IDiagnoseModel {
    private BaseListener listener;

    public DiagnoseModelImpl(BaseListener listener){
        this.listener = listener;
    }

    @Override
    public void loadDiagnoseContent(String questionCode) {
        //Map<String,String> params = new HashMap<>();

       // params.put(ParamsConstants.STEP_CODE,  code);
        //params = ParamsManage.addCommonParams(params);
        String urlParam="?Code=" + questionCode;
        Http.get(Url.URL_QUESTION_STEP + urlParam, listener);
    }

    @Override
    public void submitDiagnoseContent(String questionCode, Map<String, String> params) {
        String urlParam="?Code=" + questionCode + "&Daan=";
        String answer = "";

        for (String key : params.keySet()) {
            if(answer != "") answer += ";";
            answer += key +"=" + params.get(key);
        }
        urlParam += answer;

        Http.get(Url.URL_ANSWER_STEP + urlParam, listener);
    }

    @Override
    public void getDiagnoseResult(String answerCode)
    {
        String urlParam="?Code=" + answerCode;
        Http.get(Url.URL_ANSWER_STEP + urlParam, listener);
    }

    @Override
    public void submitUserCase(CaseItemBean caseBean){

        Map<String, String> params = new HashMap<>();

       // JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String jsonCase = JSON.toJSONString(caseBean);//, SerializerFeature.WriteDateUseDateFormat);//"{\"caseCode\":\"1111yyy\",\"userCode\":\"admin\",\"userCard\":\"admin\",\"createData\":\"2016-06-29T11:51:05\",\"doctorName\":\"好医生\",\"zdXinxi\":\"HPVJL1\",\"jieLun\":\"\",\"keShi\":\"\",\"jyJianyi\":\"\",\"jkJianyi\":\"\",\"sex\":0,\"age\":0,\"jieHunStatus\":0,\"shengYuStatus\":0}";
        //params = ParamsManage.addCommonParams(params);

        Http.postJson(Url.URL_CASE_SAVE, jsonCase, listener);
    }

}
