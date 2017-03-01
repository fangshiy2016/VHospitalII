package com.yuyang.VRHospital.bean;

/**
 * Created by yuyang on 16/4/23.
 * 辅助诊断itembean
 */
public class DiagnoseItemBean {
    private String code;
    private String zhiShiCode;
    private int floderID;
    private String title;
    private String keyWord;
    private int qType;//item类型
    private String option;//选项值XML
    private String canKao;//参考值
    private String unit;//单位
    private String desc;

    //private String content;//item内容（如果type是多选，需给出每个选项内容；如果type是下拉，需给出下拉内容；等等...）
    //private boolean showQuestion;//是否显示问号
    //private boolean showMore;//是否显示加号


    public DiagnoseItemBean() {
    }

    public DiagnoseItemBean(String code, String zhiShiCode, int floderID, String title, String keyWord, int qType, String option, String canKao, String unit, String desc) {
        this.code = code;
        this.zhiShiCode = zhiShiCode;
        this.floderID = floderID;
        this.title = title;
        this.keyWord = keyWord;
        this.qType = qType;
        this.option = option;
        this.canKao = canKao;
        this.unit = unit;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getqType() {
        return qType;
    }

    public void setqType(int qType) {
        this.qType = qType;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getCanKao() {
        return canKao;
    }

    public void setCanKao(String canKao) {
        this.canKao = canKao;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    /*
    public DiagnoseItemBean(String tag, String lable, String type, String content, boolean showQuestion, boolean showMore) {
        this.tag = tag;
        this.lable = lable;
        this.type = type;
        this.content = content;
        this.showQuestion = showQuestion;
        this.showMore = showMore;
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

    public boolean isShowQuestion() {
        return showQuestion;
    }

    public void setShowQuestion(boolean showQuestion) {
        this.showQuestion = showQuestion;
    }

    public boolean isShowMore() {
        return showMore;
    }

    public void setShowMore(boolean showMore) {
        this.showMore = showMore;
    }*/
}
