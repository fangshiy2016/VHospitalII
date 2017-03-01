package com.yuyang.VRHospital.bean;

import com.yuyang.VRHospital.BaseBean;

import java.util.List;

/**
 * Created by fanshy on 2016/7/7.
 */
public class MedicalDataByFolderBean  extends BaseBean {

    List<ZhiShiInfo> result;

    public List<ZhiShiInfo> getResult() {
        return result;
    }

    public void setResult(List<ZhiShiInfo> result) {
        this.result = result;
    }
}
