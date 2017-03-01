package com.yuyang.VRHospital.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;
import java.text.DateFormat;

/**
 * Created by fanshy on 2012/6/23.
 */
public class CaseItemBean {
    //{"id":1,"caseCode":"1111","userCode":"admin","userCard":"admin","createData":"2016-06-19T11:51:05",
    // "doctorName":"admin","zdXinxi":"HPVJL1","jieLun":"","keShi":"","jyJianyi":"","jkJianyi":"","sex":0,
    // "age":0,"jieHunStatus":0,"shengYuStatus":0}
    private int  id;
    private String caseCode;
    private String userCode;
    private String userCard;

    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date   createData;

    private String doctorName;
    private String zdXinxi;
    private String jieLun;
    private String keShi;
    private String jyJianyi;
    private int  sex;
    private int  age;
    private int  jieHunStatus;
    private int  shengYuStatus;
    private String doctorCode;
    private String  zhiShiCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public Date getCreateData() {
        return createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getZdXinxi() {
        return zdXinxi;
    }

    public void setZdXinxi(String zdXinxi) {
        this.zdXinxi = zdXinxi;
    }

    public String getJieLun() {
        return jieLun;
    }

    public void setJieLun(String jieLun) {
        this.jieLun = jieLun;
    }

    public String getKeShi() {
        return keShi;
    }

    public void setKeShi(String keShi) {
        this.keShi = keShi;
    }

    public String getJyJianyi() {
        return jyJianyi;
    }

    public void setJyJianyi(String jyJianyi) {
        this.jyJianyi = jyJianyi;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getJieHunStatus() {
        return jieHunStatus;
    }

    public void setJieHunStatus(int jieHunStatus) {
        this.jieHunStatus = jieHunStatus;
    }

    public int getShengYuStatus() {
        return shengYuStatus;
    }

    public void setShengYuStatus(int shengYuStatus) {
        this.shengYuStatus = shengYuStatus;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getZhiShiCode() {
        return zhiShiCode;
    }

    public void setZhiShiCode(String zhiShiCode) {
        this.zhiShiCode = zhiShiCode;
    }
/*
    @Override
    public int describeContents() {
        return 0;
    }
    //需要覆盖的方法
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(caseCode);
        out.writeString(userCode);
        out.writeString(userCard);
        out.writeLong(createData.getTime());
        out.writeString(doctorName);
        out.writeString(zdXinxi);
        out.writeString(jieLun);
        out.writeString(keShi);
        out.writeString(jyJianyi);
        out.writeInt(sex);
        out.writeInt(age);
        out.writeInt(jieHunStatus);
        out.writeInt(shengYuStatus);

    }
    //关键的事情
    public static final Parcelable.Creator<CaseItemBean> CREATOR = new Parcelable.Creator<CaseItemBean>() {
        public CaseItemBean createFromParcel(Parcel in) {
            return new CaseItemBean(in);
        }

        public CaseItemBean[] newArray(int size) {
            return new CaseItemBean[size];
        }
    };
    //
    private CaseItemBean(Parcel in) {
        id  =      in.readInt();
        caseCode = in.readString();
        userCode = in.readString();
        userCard = in.readString();
        createData = new Date(in.readLong());
        doctorName = in.readString();
        zdXinxi    = in.readString();
        jieLun     = in.readString();
        keShi      = in.readString();
        jyJianyi   = in.readString();
        sex        = in.readInt();
        age        = in.readInt();
        jieHunStatus  =  in.readInt();
        shengYuStatus = in.readInt();
    }
    */
}
