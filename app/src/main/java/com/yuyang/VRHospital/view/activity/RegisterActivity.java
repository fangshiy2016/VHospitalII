package com.yuyang.VRHospital.view.activity;

import android.widget.Toast;

import com.yuyang.VRHospital.BaseActivity;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.view.activity.iView.IRegisterActivity;

public class RegisterActivity extends BaseActivity implements IRegisterActivity {

    @Override
    public void initLayoutId() {
        layoutId = R.layout.activity_register;
    }

    @Override
    public void initViews() {
        showBackAction(true);
        titleView.setText("用户注册");

    }
    @Override
    public void setTitle(String title) {
        titleView.setText(title);
    }

    @Override
    public void showErrorDialog() {
        Toast.makeText(this, R.string.load_fail, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadProgress() {
        showProgressDialog();
    }

    @Override
    public void hideLoadProgress() {
        hideProgressDialog();
    }
}
