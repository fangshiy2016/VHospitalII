package com.yuyang.VRHospital.network;

import com.yuyang.VRHospital.utils.DebugUtil;

/**
 * Created by yuyang on 16/3/30.
 */
public class Url {
    /** 发布环境 */
    public static final String BASE_URI_RELEASE = "http://120.76.114.90:9526";
    /** 测试环境 */
    public static final String BASE_URI_DEBUG = "http://120.76.114.90:9526";

    public static final String IMAGE_URI_RELEASE = "http://120.76.114.90";

    public static final String WEIXIN_URI_RELEASE = "http://weixin.sc-shjk.cn/qrcode.html?id=";

    public static String getBaseUri() {
        if (DebugUtil.DEBUGABLE) {
            return BASE_URI_DEBUG;
        } else {
            return BASE_URI_RELEASE;
        }
    }
    //登陆接口
    public static final String URL_USER_LOGIN = getBaseUri() + "/api/LoginIn";
    public static final String URL_ZHISHI_GET = getBaseUri() + "/api/GetZhiShiByCode";
    public static final String URL_ZHENDUAN_STEP = getBaseUri() + "/api/GetZhenDuanStepByCode";
    public static final String URL_QUESTION_STEP = getBaseUri() + "/api/GetStepQuestionByCode";
    public static final String URL_ANSWER_STEP = getBaseUri() + "/api/GetNextStepByAnswer";
    public static final String URL_RESULT_STEP = getBaseUri() + "/api/GetZhenDuanJieLunByCode";
    public static final String URL_CASE_QUERY   = getBaseUri() + "/api/QueryUserCaseList";
    public static final String URL_ZHISHI_QUERY = getBaseUri() + "/api/GetZhiShiByPage";
    public static final String URL_CASE_SAVE    = getBaseUri() + "/api/SaveUserCase";
    public static final String URL_ZHISHI_BYFOLDERID  = getBaseUri() + "/api/GetZhiShiByFolderID";
    public static final String URL_ACTIVATE_USER  = getBaseUri() + "/api/ActivateAccount";
    public static final String URL_UPLOAD_ADDUSERHEAD = getBaseUri() + "/api/AddUserHead";
    public static final String URL_UPLOAD_EDITUSERHEAD = getBaseUri() + "/api/EditUserHead";
    public static final String URL_EXPLAIN = getBaseUri() + "/explain";


}
