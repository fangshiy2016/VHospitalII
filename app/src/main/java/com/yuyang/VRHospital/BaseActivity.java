package com.yuyang.VRHospital;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.yuyang.VRHospital.utils.SystemBarTintManager;

import com.yuyang.VRHospital.broadcast.LocalBroadcastListener;

import butterknife.ButterKnife;

/**
 * Created by yuyang on 16/3/30.
 */
public abstract class BaseActivity extends AppCompatActivity implements LocalBroadcastListener {
    protected int layoutId = R.layout.activity_base;
    protected Toolbar toolbar;
    private ProgressDialog progressDialog;

    private SystemBarTintManager tintManager;
    public TextView titleView;
    ImageView back;
    ImageView find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayoutId();
        initWindow();

        initBaseViews();
        initViews();
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(this.getResources().getColor(R.color.colorPrimary)); //设置状态栏的颜色
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onLocalReceive(Context context, Intent intent) {
    }
    /**
     * 初始化布局
     */
    public abstract void initLayoutId();

    public abstract void initViews();

    private void initBaseViews(){
        setContentView(layoutId);
        ButterKnife.bind(this);
        initAppBar();
    }

    private void initAppBar() {
        titleView = (TextView) findViewById(R.id.toolbar_title);
        back = (ImageView) findViewById(R.id.toolbar_back);
        find = (ImageView) findViewById(R.id.toolbar_find);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbar) {
            //TODO yuyang toolbar设置标题必须在setSupportAction之前 或 onResume后才会生效
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onBackClick(View view){
        finish();
    }

    public void onFindClick(View view){
        Toast.makeText(this,"find",Toast.LENGTH_SHORT).show();
    }

    protected void showBackAction(boolean show){
        if (show){
            back.setVisibility(View.VISIBLE);
        }else {
            back.setVisibility(View.GONE);
        }
    }

    public void showProgressDialog(){
        progressDialog = ProgressDialog.show(this,null,"加载中，请稍后...",false);
    }

    public void hideProgressDialog(){
        if(progressDialog != null) {
            progressDialog.hide();
        }
    }
}
