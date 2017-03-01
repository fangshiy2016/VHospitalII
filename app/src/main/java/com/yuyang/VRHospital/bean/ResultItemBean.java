package com.yuyang.VRHospital.bean;

/**
 * Created by yuyang on 16/4/23.
 * 诊断结果item
 */
public class ResultItemBean {
    private String lable;
    private String content;

    public ResultItemBean() {
    }

    public ResultItemBean(String lable, String content) {
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
