package com.yuyang.VRHospital.presenter;

import android.content.Context;
import android.widget.Toast;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.ActivateBean;
import com.yuyang.VRHospital.bean.RespnoseBean;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;
import com.yuyang.VRHospital.model.HomeLogicModelImpl;
import com.yuyang.VRHospital.model.iModel.IHomeLogicModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.IHomeLogicPresenter;
import com.yuyang.VRHospital.view.fragment.HomeFragment;
import com.yuyang.VRHospital.view.fragment.iFragment.IHomeFragment;

/**
 * Created by fanshy on 2016/7/7.
 */
public class HomeLogicPresenterImpl extends CallbackListener<ActivateBean>  implements IHomeLogicPresenter {

    private IHomeFragment fragment;
    private Context mContext;
    IHomeLogicModel homeLogicModel;

    public HomeLogicPresenterImpl(HomeFragment fragment){
        this.fragment = fragment;
        this.mContext = fragment.getContext();
        homeLogicModel = new HomeLogicModelImpl();
    }
    @Override
    public void activateAccount(String userCode, String tel, String deviceNumber){
        homeLogicModel.activateAccount(userCode, tel, deviceNumber, this);
    }

    @Override
    public void onBefore() {
        super.onBefore();
        fragment.showLoadProgress();
    }

    @Override
    public void onAfter() {
        super.onAfter();
        fragment.hideLoadProgress();
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
            fragment.setIsActivation(1);
        }

    }

    @Override
    public void onError(Exception e) {
        super.onError(e);
        onStringResult(null);
//        resultActivity.showErrorDialog();
    }

    @Override
    public void onStringResult(String result) {
        super.onStringResult(result);

    }
}
