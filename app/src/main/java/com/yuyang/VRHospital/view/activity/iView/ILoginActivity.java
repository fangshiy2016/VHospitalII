package com.yuyang.VRHospital.view.activity.iView;

import com.yuyang.VRHospital.bean.LoginBean;

/**
 * Created by yuyang on 16/4/5.
 */
public interface ILoginActivity {
    String getUserName();
    String getPassword();
    String getDeviceNumber();
    /**
     * 登陆成功页面跳转
     * @param user
     */
    void toMainActivity(LoginBean user);

    void showHttpProgress();
    void closeHttpProgress();
}
