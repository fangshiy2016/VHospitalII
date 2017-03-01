package com.yuyang.VRHospital.bean;

import com.yuyang.VRHospital.BaseBean;

import java.util.List;

/**
 * Created by fanshy on 2016/6/19.
 */
public class DiagnoseBean extends BaseBean {

    private List<DiagnoseItemBean> result;
    public List<DiagnoseItemBean> getResult() {
        return result;
    }

    public void setResult(List<DiagnoseItemBean> result) {
        this.result = result;
    }



}
