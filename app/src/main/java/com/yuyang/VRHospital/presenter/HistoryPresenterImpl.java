package com.yuyang.VRHospital.presenter;

import com.yuyang.VRHospital.bean.HistoryItemBean;
import com.yuyang.VRHospital.model.HistoryModelImpl;
import com.yuyang.VRHospital.model.iModel.IHistoryModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.IHistoryPresenter;
import com.yuyang.VRHospital.view.activity.iView.IHistoryActivity;
import com.yuyang.VRHospital.view.form.DynamicFormHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyang on 2016/4/24.
 */
public class HistoryPresenterImpl implements IHistoryPresenter {
    private IHistoryActivity historyActivity;
    private IHistoryModel historyModel;

    public HistoryPresenterImpl(IHistoryActivity activity){
        this.historyActivity = activity;
        this.historyModel = new HistoryModelImpl();
    }

    @Override
    public void getHistoryContent(String tag) {
        historyModel.loadHistoryContent(tag, new HistoryContentCallBack());
    }

    class HistoryContentCallBack extends CallbackListener<List<HistoryItemBean>>{
        @Override
        public void onBefore() {
            super.onBefore();
            historyActivity.showLoadProgress();
        }

        @Override
        public void onAfter() {
            super.onAfter();
            historyActivity.hideLoadProgress();
        }

        @Override
        public void onSuccess(List<HistoryItemBean> result) {
            super.onSuccess(result);
            historyActivity.setHistoryContent(result);
        }

        @Override
        public void onError(Exception e) {
            super.onError(e);
//            historyActivity.showErrorDialog();
            onStringResult(null);
        }

        //TODO yuyang 测试完成后删除
        @Override
        public void onStringResult(String result) {
            super.onStringResult(result);
            HistoryItemBean editItem = new HistoryItemBean("体重(kg)","体重(kg)：",
                    DynamicFormHelper.CELL_TYPE_EDIT, "");
            HistoryItemBean spinnerItem = new HistoryItemBean("HVS-L-LGM","HVS-L-LGM：",
                    DynamicFormHelper.CELL_TYPE_SPINNER, "－请选择－,阴性,阳性");
            List<HistoryItemBean> itemBeans = new ArrayList<>();
            itemBeans.add(spinnerItem);
            itemBeans.add(editItem);
            onSuccess(itemBeans);
        }
    }
}
