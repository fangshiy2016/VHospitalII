package com.yuyang.VRHospital.bean;

import java.io.Serializable;

/**
 * Created by fanshy on 2016/6/26.
 */
public class KeshiInfo implements Serializable{
    //{"id":2,"code":"lanke1","zyCode":"fenglei2","name":"男科","desc":"男科","zhiShiCode":""
    int     id;
    String  code;
    String  zyCode;
    String  name;
    String  desc;
    String  zhiShiCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZyCode() {
        return zyCode;
    }

    public void setZyCode(String zyCode) {
        this.zyCode = zyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getZhiShiCode() {
        return zhiShiCode;
    }

    public void setZhiShiCode(String zhiShiCode) {
        this.zhiShiCode = zhiShiCode;
    }
}
