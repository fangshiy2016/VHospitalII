package com.yuyang.VRHospital;

import java.io.Serializable;

/**
 * Created by yuyang on 16/4/5.
 *
 * 根据我个人之前的项目定义的基础Bean
 */
public class BaseBean implements Serializable {
    /** 错误码（0：正常数据，非0：错误） */
    protected int status;

    protected String code;

    /** 根据code定义的消息内容 */
    protected String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
