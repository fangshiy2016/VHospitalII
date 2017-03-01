package com.yuyang.VRHospital.bean;

/**
 * Created by fanshy on 2016/6/26.
 */
public class ZhiLiaoInfo {
    //[{"code":"HPVZL1","title":"HPV 治疗 ","keyWord":"HPV 治疗 ","content":"HPV 治疗 HPV 治疗 HPV 治疗
    //<br>","floderID":79}
    String  code;
    String  title;
    String  keyWord;
    String  content;

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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
