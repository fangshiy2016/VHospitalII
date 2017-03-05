package com.yuyang.VRHospital.view.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyang.VRHospital.BaseActivity;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.LoginBean;
import com.yuyang.VRHospital.bean.zhenDuanTemplate;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;
import com.yuyang.VRHospital.network.http.BaseHttp;
import com.yuyang.VRHospital.presenter.CachePresenterImpl;
import com.yuyang.VRHospital.presenter.HomeLogicPresenterImpl;
import com.yuyang.VRHospital.presenter.LoginPresenterImpl;
import com.yuyang.VRHospital.presenter.iPresenter.IHomeLogicPresenter;
import com.yuyang.VRHospital.utils.ImageLoaderUtils;
import com.yuyang.VRHospital.view.CircleImageView;
import com.yuyang.VRHospital.view.activity.iView.ILoginActivity;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yuyang on 16/3/30.
 */
public class LoginActivity extends BaseActivity implements ILoginActivity {

    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.toolbar_back)
    ImageView mToolbarBack;

    @Bind(R.id.et_user_email)
    EditText user_eamil;
    @Bind(R.id.et_user_password)
    EditText user_pass;

    @Bind(R.id.btn_login)
    Button mBtnLogin;

    @Bind(R.id.btn_register)
    Button btnRegister;

    @Bind(R.id.frag_doc_mine_head)
    CircleImageView docHeaderImage;

    @Bind(R.id.home_doc_name)
    TextView mUserName;

    ProgressDialog progressDialog;
    private CachePresenterImpl cachePresenter;
    private LoginPresenterImpl loginPresenter;
    private PopupWindow popupWindow;
    private String mUserCode;
    private String mTelNum;

    @Override
    public void initLayoutId() {
        layoutId = R.layout.activity_login;
    }

    @Override
    public void initViews() {
        loginPresenter = new LoginPresenterImpl(this);
        cachePresenter = new CachePresenterImpl(this);
        progressDialog = new ProgressDialog(this);
        title.setText(R.string.login_bar_title);
        btnRegister.setVisibility(View.INVISIBLE);

        String usercode = SPDao.getSharedPreferences(SPKey.USER_CODE, "");
        String password = SPDao.getSharedPreferences(SPKey.PASSWORD, "");
        user_eamil.setText(usercode);
        user_pass.setText(password);

        String headPath = SPDao.getSharedPreferences(SPKey.HEADER_IMAGE, "");
        if(!headPath.isEmpty()){
            docHeaderImage.setImageURI(Uri.fromFile(new File(headPath)));
        }

        mUserName.setText(SPDao.getSharedPreferences(SPKey.USER_NAME, ""));
        mToolbarBack.setVisibility(View.VISIBLE);

        View popupContent = LayoutInflater.from(this).inflate(R.layout.layout_activation_popup,null);
        popupWindow = new PopupWindow(popupContent,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        initPopupItemClickListener(popupContent);
    }

    private void toastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_register,R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_register:
                gotoRegisterActivity();
                break;
            case R.id.btn_login:
                loginPresenter.doLogin();
                //gotoMainActivity();
                break;
        }
    }

    @Override
    public String getDeviceNumber() {
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = tm.getDeviceId();//获取智能设备唯一编号
        return deviceid;
    }

    @Override
    public String getUserName() {
        return user_eamil.getText().toString();
    }

    @Override
    public String getPassword() {
        return user_pass.getText().toString();
    }

    @Override
    public void toMainActivity(LoginBean user) {

        int loginStatus = Integer.parseInt(user.getCode());
       // toastMsg("登陆成功，页面跳转");
        SPDao.saveSharedPreferences(SPKey.USER_NAME, user.getResult().getName());
        SPDao.saveSharedPreferences(SPKey.USER_CODE,  user.getResult().getCode());
        //SPDao.saveSharedPreferences(SPKey.PASSWORD,   user.getResult().getPassWord());

        mUserName.setText(getResources().getString(R.string.home_welcome, user.getResult().getName()));
        SPDao.saveSharedPreferences(SPKey.USER_KESHI,    user.getResult().getProfession());
        SPDao.saveSharedPreferences(SPKey.IS_ACTIVATION, Integer.parseInt(user.getCode()));

        String localPath = ImageLoaderUtils.getHeadPicPath();
        String headerImage = user.getResult().getHeaderImage();
        if(headerImage != null){
            cachePresenter.downloadImage(headerImage, localPath);
        }

        SPDao.saveObject(SPKey.ZHENDUN_TEMPLATES, user.getResult());

        List<zhenDuanTemplate> zdTemp = user.getResult().getZhenDuanTemplates();
        if(zdTemp != null && zdTemp.size() > 0)
            SPDao.saveSharedPreferences(SPKey.QUESTION_CODE, zdTemp.get(0).getCode());

        switch (loginStatus) {
            case 1:
                break;
            case 2:
                Toast.makeText(this, R.string.home_pass_error, Toast.LENGTH_SHORT).show();
                return ;
            case 3:
                mUserCode =  user.getResult().getCode();
                mTelNum = user.getResult().getTel();
                if (Build.VERSION.SDK_INT >= 20) {
                    popupWindow.showAtLocation(mBtnLogin, Gravity.BOTTOM, 0, getSoftButtonsBarHeight());
                }else{
                    popupWindow.showAtLocation(mBtnLogin, Gravity.BOTTOM, 0, 0);
                }
                return ;
            case 4:
                Toast.makeText(this, R.string.home_not_bangding, Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, R.string.home_account_invalid, Toast.LENGTH_SHORT).show();
                break;
        }
        gotoMainActivity(user.getCode());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        //TODO yuyang 这个方法获取可能不是真实屏幕的高度
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //TODO yuyang 获取当前屏幕的真实高度
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

    private boolean initPopupItemClickListener(View popupContent) {
        View tvActiv = popupContent.findViewById(R.id.view_actaivation);
        tvActiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String deviceid = tm.getDeviceId();//获取智能设备唯一编号
                String te1  = tm.getLine1Number();//获取本机号码
                //String imei = tm.getSimSerialNumber();//获得SIM卡的序号
                //String imsi = tm.getSubscriberId();//得到用户Id
                cachePresenter.activateAccount(mUserCode, mTelNum, deviceid);

            }
        });
        View cancel = popupContent.findViewById(R.id.view_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        return true;
    }

    public void showHttpProgress() {

        showHttpProDialog("登陆中");
    }

    public void closeHttpProgress() {
        closeHttpProDialog();
    }

    public void httpProDialogDismissOpt() {
        httpProDialogDismissOpt();
        loginPresenter.cancelHttp();
    }
    public void closeHttpProDialog()
    {
        if(progressDialog != null)
        {
            progressDialog.dismiss();
        }
    }

    @Override
    public void toMainActivity(boolean isActive){
        popupWindow.dismiss();
        if(isActive){
            gotoMainActivity("1");
        }
        else {
            gotoMainActivity("3");
        }
    }

    /**
     * 跳转至主页
     */
    private void gotoMainActivity(String loginCode) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("login_code", loginCode);
        startActivity(intent);
        finish();
    }

    private void gotoRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void showHttpProDialog(String str)
    {
        if(progressDialog != null)
        {
            progressDialog.setMessage(str);
            progressDialog.show();
        }
    }

}
