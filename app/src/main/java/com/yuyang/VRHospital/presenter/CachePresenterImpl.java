package com.yuyang.VRHospital.presenter;

import com.yuyang.VRHospital.bean.ResultBean;
import com.yuyang.VRHospital.model.ResultModelImpl;
import com.yuyang.VRHospital.model.iModel.IResultModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.ICachePresenter;
import com.yuyang.VRHospital.view.activity.iView.IDiagnoseResultActivity;

/**
 * Created by yuyang on 16/4/23.
 */
public class CachePresenterImpl extends CallbackListener<String> implements ICachePresenter {
    private IResultModel resultModel;

    public CachePresenterImpl(){
        resultActivity = activity;
        resultModel = new ResultModelImpl(this);
    }

    @Override
    public void loadResult(String tag) {
        resultModel.loadResult(tag);
    }

    @Override
    public void onBefore() {
        super.onBefore();
        resultActivity.showLoadProgress();
    }

    @Override
    public void onAfter() {
        super.onAfter();
        resultActivity.hideLoadProgress();
    }

    @Override
    public void onSuccess(ResultBean result) {
        super.onSuccess(result);
        if(result.getStatus() == 200 && result.getResult() != null){

            resultActivity.setContent(result);
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
       /*
        ResultItemBean itemBean1 = new ResultItemBean("HVS-L-Igm:","阳性");
        ResultItemBean itemBean2 = new ResultItemBean("HSV-JJ-Igm:","阴性");
        ResultItemBean itemBean3 = new ResultItemBean("TOX-Igm:","阳性");
        ResultItemBean itemBean4 = new ResultItemBean("CMV-Igm:","阴性");
        List<ResultItemBean> itemBeans = new ArrayList<>();
        itemBeans.add(itemBean1);
        itemBeans.add(itemBean2);
        itemBeans.add(itemBean3);
        itemBeans.add(itemBean4);
        ResultBean resultBean = new ResultBean("男","23岁","已婚已育","http://img.my.csdn.net/uploads/201604/18/1460943716_5112.jpg",
                "ED34D","1、进行血常规检查；\n2、有可能出现xxx病患的转型；\n3、如果xxx指标异常需要进一步检查；",
                itemBeans);
        onSuccess(resultBean);
        */
    }
}
