package com.yuyang.VRHospital.presenter;

import com.yuyang.VRHospital.bean.ResultBean;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;
import com.yuyang.VRHospital.model.CacheModelImpl;
import com.yuyang.VRHospital.model.ResultModelImpl;
import com.yuyang.VRHospital.model.iModel.ICacheModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.ICachePresenter;
import com.yuyang.VRHospital.view.activity.iView.IDiagnoseResultActivity;

/**
 * Created by yuyang on 16/4/23.
 */
public class CachePresenterImpl extends CallbackListener<String> implements ICachePresenter {
    private ICacheModel cacheModel;

    public CachePresenterImpl(){
        cacheModel = new CacheModelImpl(this);
    }

    @Override
    public void downloadImage(String imageUrl, String localPath) {
        cacheModel.downloadImage(imageUrl, localPath);
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
    public void onSuccess(String resultPath) {
        super.onSuccess(resultPath);

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
