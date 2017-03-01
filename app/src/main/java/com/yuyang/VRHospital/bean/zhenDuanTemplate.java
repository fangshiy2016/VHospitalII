package com.yuyang.VRHospital.bean;

/**
 * Created by fanshy on 2016/7/3.
 */
public class zhenDuanTemplate {
    //{"code":"HPV_ZDBZ1","title":"HPV诊断 ","zhiShiCode":"HPV_CS_1","floderID":77,"desc":""}
    String  code;
    String  title;
    String  zhiShiCode;
    int     floderID;
    String  desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZhiShiCode() {
        return zhiShiCode;
    }

    public void setZhiShiCode(String zhiShiCode) {
        this.zhiShiCode = zhiShiCode;
    }

    public int getFloderID() {
        return floderID;
    }

    public void setFloderID(int floderID) {
        this.floderID = floderID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
