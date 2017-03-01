package com.yuyang.VRHospital.presenter;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.ExplainBean;
import com.yuyang.VRHospital.bean.ExplainItemBean;
import com.yuyang.VRHospital.model.ExplainModel;
import com.yuyang.VRHospital.model.iModel.IExplainModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.IExplainPresenter;
import com.yuyang.VRHospital.view.activity.iView.IDiagnoseExplainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyang on 16/4/20.
 */
public class ExplainPresenterImpl extends CallbackListener<ExplainBean> implements IExplainPresenter {
    private IDiagnoseExplainActivity explainActivity;
    private IExplainModel explainModel;

    public ExplainPresenterImpl(IDiagnoseExplainActivity activity){
        this.explainActivity = activity;
        this.explainModel = new ExplainModel(this);
    }

    @Override
    public void loadExplain(String label) {
        explainModel.loadExplainBean(label);
    }

    @Override
    public void onBefore() {
        super.onBefore();
        explainActivity.showLoadProgress();
    }

    @Override
    public void onAfter() {
        super.onAfter();
        explainActivity.hideLoadProgress();
    }

    @Override
    public void onSuccess(ExplainBean result) {
        super.onSuccess(result);
        if(result != null && result.getResult() != null){

            explainActivity.setOverview(result.getResult().getTitle(), result.getResult().getVipCotent());//result.getResult().getDtContent());//result.getOverview());
            /*
            if(result.getItemBeans() != null){
                boolean deepColor = false;
                for(ExplainItemBean itemBean:result.getItemBeans()){
                    if (deepColor) {
                        explainActivity.setItems(itemBean, R.color.window_deep_bg);
                        deepColor = false;
                    }else {
                        explainActivity.setItems(itemBean, R.color.window_background);
                        deepColor = true;
                    }
                }
            }
            */
        }else {
            onError(new Exception());
        }
    }

    @Override
    public void onError(Exception e) {
        super.onError(e);
        onStringResult(null);
//        explainActivity.showErrorDialog();
    }

    //TODO yuyang 测试数据
    @Override
    public void onStringResult(String result) {
        super.onStringResult(result);
        /*List<ExplainItemBean> itemBeans = new ArrayList<>();
        itemBeans.add(new ExplainItemBean("中文名","HSV"));
        itemBeans.add(new ExplainItemBean("归属","疱疹病毒科"));
        itemBeans.add(new ExplainItemBean("定义","单纯疱疹病毒"));
        itemBeans.add(new ExplainItemBean("颗粒", "大小约180纳米"));
        ExplainBean explainBean = new ExplainBean(itemBeans,"在性病医院临床证明单纯疱疹病毒（herpes simplex virus 简称HSV）" +
                "是人类最常见的病原体，人是其唯一的自然宿主。此病毒存在于病人、恢复者或者是健康带菌者的水疱疤液、" +
                "唾液及粪便中，传播方式主要是直接接触传染，亦可通过被唾液污染的餐具而间接传染。" +
                "HSV感染现已成为世界上第四大传染病。");
        onSuccess(explainBean);
        */
    }
}
