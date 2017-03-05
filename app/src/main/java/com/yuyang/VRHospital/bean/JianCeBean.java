package com.yuyang.VRHospital.bean;

/**
 * Created by fanshy on 2016/6/26.
 */
public class JianCeBean {
    //{"floderID":78,"id":5,"code":" HPV JC1","name":"HPV 血常规 ","zsCode":"","desc":"HPV 血常规 HPV 血常规 HPV 血常规 HPV 血常规
    //<br>"}
    int     id;
    int     floderID;
    String  code;
    String  name;
    String  zsCode;
    String  desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloderID() {
        return floderID;
    }

    public void setfloderID(int folderID) {
        this.floderID = folderID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZsCode() {
        return zsCode;
    }

    public void setZsCode(String zsCode) {
        this.zsCode = zsCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
