package com.yuyang.VRHospital.presenter;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.yuyang.VRHospital.bean.LoginBean;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;
import com.yuyang.VRHospital.model.UserModelImpl;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.ILoginPresenter;
import com.yuyang.VRHospital.view.activity.LoginActivity;
import com.yuyang.VRHospital.view.activity.iView.ILoginActivity;

/**
 * Created by yuyang on 16/4/5.
 */
public class LoginPresenterImpl implements ILoginPresenter {
    private ILoginActivity loginActivity;
    private Context mContext;

    public LoginPresenterImpl(LoginActivity context) {
        loginActivity = context;
        mContext = context;
    }

    @Override
    public void doLogin() {
        UserModelImpl userModel = new UserModelImpl(new LoginCallBack(), mContext);
        String username = loginActivity.getUserName();
        String password = loginActivity.getPassword();
        String deviceid = loginActivity.getDeviceNumber();

        SPDao.saveSharedPreferences(SPKey.USER_CODE, username);
        SPDao.saveSharedPreferences(SPKey.PASSWORD, password);
        //TODO yuyang 加密密码
        //String encryptPassword = EncryptPwd.encryptPassword(password);


        userModel.login(username, password/*encryptPassword*/, 1, deviceid);
    }

    @Override
    public void cancelHttp() {

    }

    class LoginCallBack extends CallbackListener<LoginBean>{
        @Override
        public void onBefore() {
            super.onBefore();
            loginActivity.showHttpProgress();
        }

        @Override
        public void onAfter() {
            super.onAfter();
            loginActivity.closeHttpProgress();
        }

        @Override
        public void onError(Exception e) {
            super.onError(e);
            Toast.makeText(mContext, "登陆失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(LoginBean result) {
            super.onSuccess(result);
            if(result.getResult() != null) {
                if(result.getCode().compareTo("1") == 0) {
                    Toast.makeText(mContext, result.getResult().getName() + "登陆成功", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(mContext, "登陆失败:" + result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(mContext, "登陆失败:" + result.getMessage(), Toast.LENGTH_SHORT).show();
            }
            loginActivity.toMainActivity(result);
        }
    }
}
