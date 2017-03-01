package com.yuyang.VRHospital.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;
import com.yuyang.VRHospital.utils.AppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuyang on 16/4/7.
 */
public class SplashActivity extends AppCompatActivity {
    @Bind(R.id.splash_image)
    ImageView mSplashImg;
    @Bind(R.id.splash_version_name)
    TextView mVersionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        mSplashImg.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                goNext();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mVersionName.setText(getResources().getString(R.string.splash_version_name, AppUtils.getAppVersion()));
    }

    private void goNext() {
        boolean isNewVersion = SPDao.getSharedPreferences(SPKey.NEW_VERSION, true);
        if(isNewVersion){
            gotoLeadActivity();
        }else {

            gotoLoginActivity();
            //gotoMainActivity();
        }
    }

    /**
     * 跳转至登陆页面
     */
    private void gotoLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    /**
     * 跳转至主页
     */
    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转至引导页
     */
    private void gotoLeadActivity() {
        Intent intent = new Intent(this, LeadActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
