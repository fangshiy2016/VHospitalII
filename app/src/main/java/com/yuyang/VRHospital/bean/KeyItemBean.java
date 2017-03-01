package com.yuyang.VRHospital.bean;

/**
 * Created by fanshy on 2016/6/27.
 */
public class KeyItemBean {
    private String lable;
    private String content;

    public KeyItemBean() {
    }

    public KeyItemBean(String lable, String content) {
        this.lable = lable;
        this.content = content;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
