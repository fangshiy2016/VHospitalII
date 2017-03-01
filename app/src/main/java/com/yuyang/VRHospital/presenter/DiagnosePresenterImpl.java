package com.yuyang.VRHospital.presenter;

import com.yuyang.VRHospital.bean.DiagnoseBean;
import com.yuyang.VRHospital.model.DiagnoseModelImpl;
import com.yuyang.VRHospital.model.iModel.IDiagnoseModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.IDiagnosePresenter;
import com.yuyang.VRHospital.view.fragment.iFragment.IDiagnoseFragment;

import java.util.Map;

/**
 * Created by yuyang on 16/4/24.
 */
public class DiagnosePresenterImpl extends CallbackListener<DiagnoseBean> implements IDiagnosePresenter {
    private IDiagnoseFragment diagnoseFragment;
    private IDiagnoseModel diagnoseModel;

    public DiagnosePresenterImpl(IDiagnoseFragment iDiagnoseFragment){
        this.diagnoseFragment = iDiagnoseFragment;
        diagnoseModel = new DiagnoseModelImpl(this);
    }

    @Override
    public void getDiagnoseContent(String questionCode) {
        diagnoseModel.loadDiagnoseContent(questionCode);
    }

    @Override
    public void submitDiagnoseContent(String questionCode, Map<String, String> params) {
        diagnoseModel.submitDiagnoseContent(questionCode, params);
    }

    //@Override
    //public void getDiagnoseResult(String answerCode){
    //    diagnoseModel.getDiagnoseResult(answerCode);
    //}

    @Override
    public void onBefore() {
        super.onBefore();
        diagnoseFragment.showLoadProgress();
    }

    @Override
    public void onAfter() {
        super.onAfter();
        diagnoseFragment.hideLoadProgress();
    }

    @Override
    public void onSuccess(DiagnoseBean result) {

        super.onSuccess(result);
        if(result.getStatus() == 200 && result.getResult() != null){
            diagnoseFragment.setDiagnoseContent(result.getResult());
        }
    }

    @Override
    public void onError(Exception e) {
//        super.onError(e);
//        diagnoseFragment.showErrorDialog();
        onStringResult(null);
    }

    //TODO yuyang 测试数据，是用完成后删除
    @Override
    public void onStringResult(String result) {
        super.onStringResult(result);
        /*
        DiagnoseItemBean editItem = new DiagnoseItemBean("体重(kg)","体重(kg)：",
                DynamicFormHelper.CELL_TYPE_EDIT,
                "",true,true);
        DiagnoseItemBean dateItem = new DiagnoseItemBean("开始时间","开始时间：",
                DynamicFormHelper.CELL_TYPE_DATE,
                "",true,false);
        DiagnoseItemBean spinnerItem = new DiagnoseItemBean("HVS-L-LGM","HVS-L-LGM：",
                DynamicFormHelper.CELL_TYPE_SPINNER,
                "－请选择－,阴性,阳性",true,true);
        DiagnoseItemBean radioItem = new DiagnoseItemBean("是否便秘","是否便秘：",
                DynamicFormHelper.CELL_TYPE_RADIO,
                "是,否",false,true);
        DiagnoseItemBean checkBoxItem = new DiagnoseItemBean("家族史","家族史：",
                DynamicFormHelper.CELL_TYPE_CHECKBOX,
                "乙肝,甲肝,心脏病,高血压",true,true);
        List<DiagnoseItemBean> itemBeans = new ArrayList<>();


        itemBeans.add(editItem);
        itemBeans.add(dateItem);
        itemBeans.add(spinnerItem);
        itemBeans.add(radioItem);
        itemBeans.add(checkBoxItem);

        onSuccess(itemBeans);
        */
    }
}
