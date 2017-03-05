package com.yuyang.VRHospital.bean;

import com.yuyang.VRHospital.BaseBean;

/**
 * Created by yuyang on 16/4/5.
 */
//TODO yuyang 以后根据需求随时更改该类的成员变量，这里只作为测试使用
public class LoginBean extends BaseBean {

    private LoginInfoBean result;
    public LoginInfoBean getResult() {
        return result;
    }

    public void setResult(LoginInfoBean result) {
        this.result = result;
    }

    //public String getMessage() {
 //       return message;
  //  }

    //public void setMessage(String message) {
     //   this.message = message;
    //}
}
