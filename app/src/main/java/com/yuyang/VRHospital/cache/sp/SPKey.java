package com.yuyang.VRHospital.cache.sp;

public interface SPKey {
    /**是否是新的版本 */
    String NEW_VERSION = "new_version";
    /** 登录用户名*/
    String USER_NAME = "user_name";
    /** 用户编码*/
    String USER_CODE = "user_code";
    String PASSWORD = "user_pass";
    String USER_KESHI = "user_keshi";
    String HEADER_IMAGE = "header_image";
    String IS_ACTIVATION = "isActivation";
    /** 问题步骤编码*/
    String QUESTION_CODE = "question_code";
    /** 知识模板编码*/
    String ZHISHI_CODE = "zhishi_code";//默认诊断模板

    String USR_SEX     = "user_sex";

    String USR_AGE     = "user_age";

    String USR_MARRIGGE   = "user_marriage";

    String ZHENDUN_TEMPLATES   = "zhenDuanTemplates";

    String ZHENDUN_INFO   = "zhenDuanInfo";
}
