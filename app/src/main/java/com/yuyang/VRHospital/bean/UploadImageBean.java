package com.yuyang.VRHospital.bean;


import java.io.Serializable;

public class UploadImageBean implements Serializable{

    private int     status;
    private String  result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
