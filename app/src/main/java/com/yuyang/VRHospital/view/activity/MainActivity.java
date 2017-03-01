package com.yuyang.VRHospital.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yuyang.VRHospital.BaseActivity;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.broadcast.LocalBroadcastReceiver;
import com.yuyang.VRHospital.view.fragment.CaseDbFragment;
import com.yuyang.VRHospital.view.fragment.DiagnoseFragment;
import com.yuyang.VRHospital.view.fragment.HomeFragment;
import com.yuyang.VRHospital.view.fragment.MedicalDataDbFragment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by yuyang on 16/4/8.
 */
public class MainActivity extends BaseActivity {
    public static final int DIAGNOSE_TYPE = 1;
    public static final int MEDICAL_TYPE = 2;
    public static final int CASE_TYPE = 3;

    @Bind(R.id.main_group)
    RadioGroup mainGroup;
    @Bind(R.id.realtabcontent)
    FrameLayout content;
    @Bind(R.id.main_home)
    RadioButton home;
    @Bind(R.id.main_diagnose)
    RadioButton diagnose;
    @Bind(R.id.main_medical_data)
    RadioButton medical;
    @Bind(R.id.main_case_db)
    RadioButton caseDb;

    private int mLoginStatus;
    private HomeFragment homeFragment;
    private DiagnoseFragment diagnoseFragment;
    private MedicalDataDbFragment medicalDataDbFragment;
    private CaseDbFragment caseDbfragment;
    private Activity theThis;
    private FragmentManager fragmentManager;
    @Override
    public void initLayoutId() {
        layoutId = R.layout.activity_main;
    }

    @Override
    public void initViews() {
        fragmentManager = getSupportFragmentManager();

        homeFragment = new HomeFragment();
        diagnoseFragment = new DiagnoseFragment();
        medicalDataDbFragment = new MedicalDataDbFragment();
        caseDbfragment = new CaseDbFragment();

        String loginCode = getIntent().getStringExtra("login_code");
        mLoginStatus = Integer.parseInt(loginCode);

        homeFragment.setIsActivation(mLoginStatus);

        theThis = this;
        mainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (mLoginStatus) {
                    case 1:
                        break;
                    case 2:
                        Toast.makeText(theThis, R.string.home_pass_error, Toast.LENGTH_SHORT).show();
                        return ;
                    case 3:
                        Toast.makeText(theThis, R.string.home_not_active, Toast.LENGTH_SHORT).show();
                        return ;
                    case 4:
                        Toast.makeText(theThis, R.string.home_not_bangding, Toast.LENGTH_SHORT).show();
                        return ;
                    case 5:
                        Toast.makeText(theThis, R.string.home_account_invalid, Toast.LENGTH_SHORT).show();
                        return ;
                }

                switch (checkedId){
                    case R.id.main_home:
                        changeFragment(homeFragment);
                        break;
                    case R.id.main_diagnose:
                        changeFragment(diagnoseFragment);
                        break;
                    case R.id.main_medical_data:
                        changeFragment(medicalDataDbFragment);
                        break;
                    case R.id.main_case_db:
                        changeFragment(caseDbfragment);
                        break;
                }
            }
        });
        home.setChecked(true);
        changeFragment(homeFragment);
    }

    public void setIsActivation(int isActivation){
        mLoginStatus = isActivation;
    }

    public void changeFragment(int fragment_type){
        switch (fragment_type){
            case DIAGNOSE_TYPE:
                diagnose.setChecked(true);
                break;
            case MEDICAL_TYPE:
                medical.setChecked(true);
                break;
            case CASE_TYPE:
                caseDb.setChecked(true);
                break;
        }
    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        List<Fragment> fragmentList = fragmentManager.getFragments();
        if(fragmentList != null) {
            for (Fragment fragment1 : fragmentList) {
                fragmentTransaction.hide(fragment1);
            }
        }
        if(fragment.isAdded()){
            fragmentTransaction.show(fragment);
        }else {
            fragmentTransaction.add(R.id.realtabcontent,fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onLocalReceive(Context context, Intent intent) {
        super.onLocalReceive(context, intent);
        if (intent != null && (LocalBroadcastReceiver.LocalIntent.EVENT_CLOSE_MAIN_ACTIVITY.equals(intent.getStringExtra(LocalBroadcastReceiver.LocalIntent.EXTRA_EVENT)))) {
            finish();
        }
    }
}
