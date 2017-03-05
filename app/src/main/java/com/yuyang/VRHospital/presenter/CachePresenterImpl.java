package com.yuyang.VRHospital.presenter;

import android.content.Context;
import android.widget.Toast;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.ActivateBean;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;
import com.yuyang.VRHospital.model.CacheModelImpl;
import com.yuyang.VRHospital.model.iModel.ICacheModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.ICachePresenter;
import com.yuyang.VRHospital.view.activity.LoginActivity;
import com.yuyang.VRHospital.view.activity.iView.ILoginActivity;

/**
 * Created by yuyang on 16/4/23.
 */
public class CachePresenterImpl extends CallbackListener<ActivateBean> implements ICachePresenter {
    private ICacheModel cacheModel;
    Context mContext;
    private ILoginActivity loginActivity;

    public CachePresenterImpl(LoginActivity context) {
        mContext = context;
        loginActivity = context;
        cacheModel = new CacheModelImpl(this);
    }

    @Override
    public void downloadImage(String imageUrl, String localPath) {
        cacheModel.downloadImage(imageUrl, localPath);
    }

    @Override
    public void activateAccount(String userCode, String tel, String deviceNumber){
        cacheModel.activateAccount(userCode, tel, deviceNumber, this);
    }

    @Override
    public void onBefore() {
        super.onBefore();
    }

    @Override
    public void onAfter() {
        super.onAfter();
    }

    @Override
    public void onSuccess(ActivateBean result) {
        super.onSuccess(result);

        if(result.getStatus() != 200 || result.isResult() == false){

            Toast.makeText(mContext, result.getMessage(), Toast.LENGTH_SHORT).show();
        }
        else{
            SPDao.saveSharedPreferences(SPKey.IS_ACTIVATION, 1);
            Toast.makeText(mContext, R.string.activation_sucess, Toast.LENGTH_SHORT).show();

        }
        loginActivity.toMainActivity(result.isResult());
    }

    @Override
    public void onDownloadFinish(String path) {
        if(path != null){

            SPDao.saveSharedPreferences(SPKey.HEADER_IMAGE, path);
        }
    }


    @Override
    public void onError(Exception e) {
        super.onError(e);
        onStringResult(null);
    }

    @Override
    public void onStringResult(String result) {
        super.onStringResult(result);
    }
}
