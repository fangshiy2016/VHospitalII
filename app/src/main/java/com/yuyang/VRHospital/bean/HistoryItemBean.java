package com.yuyang.VRHospital.bean;

/**
 * Created by yuyang on 2016/4/24.
 */
public class HistoryItemBean {
    private String tag;
    private String lable;
    private String type;//item类型
    private String content;//item内容（如果type是多选，需给出每个选项内容；如果type是下拉，需给出下拉内容；等等...）

    public HistoryItemBean() {
    }

    public HistoryItemBean(String tag, String lable, String type, String content) {
        this.tag = tag;
        this.lable = lable;
        this.type = type;
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
