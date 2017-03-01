package com.yuyang.VRHospital.bean;

/**
 * Created by fanshy on 2016/6/26.
 */
public class ZhiShiInfo {
    //{"code":"HPV_CS_1","floderID":1,"title":"测试知识","keyWord":"测试知识 ","vipCotent":"测试知识 测试知识 测试知识 测试知识 测试知识 ","userContent":"测试知识 测试知识 测试知识
    //<br>","dtContent":"测试知识 测试知识 测试知识
    //<br>"}
    String  code;
    int     folderID;
    String  title;
    String  keyWord;
    String  vipCotent;
    String  userContent;
    String  dtContent;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFolderID() {
        return folderID;
    }

    public void setFolderID(int folderID) {
        this.folderID = folderID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getVipCotent() {
        return vipCotent;
    }

    public void setVipCotent(String vipCotent) {
        this.vipCotent = vipCotent;
    }

    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent;
    }

    public String getDtContent() {
        return dtContent;
    }

    public void setDtContent(String dtContent) {
        this.dtContent = dtContent;
    }
}
