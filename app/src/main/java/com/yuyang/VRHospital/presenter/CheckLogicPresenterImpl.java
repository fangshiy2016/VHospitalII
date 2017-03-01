package com.yuyang.VRHospital.presenter;

import com.yuyang.VRHospital.bean.CheckLogicBean;
import com.yuyang.VRHospital.model.DiagnoseModelImpl;
import com.yuyang.VRHospital.model.iModel.IDiagnoseModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.IDiagnosePresenter;
import com.yuyang.VRHospital.view.fragment.iFragment.IDiagnoseFragment;

import java.util.Map;

/**
 * Created by yuyang on 16/4/23.
 */
public class CheckLogicPresenterImpl extends CallbackListener<CheckLogicBean> implements IDiagnosePresenter {
    private IDiagnoseFragment diagnoseFragment;
    private IDiagnoseModel diagnoseModel;

    public CheckLogicPresenterImpl(IDiagnoseFragment iDiagnoseFragment){
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

    @Override
    public void onBefore() {
        super.onBefore();
        diagnoseFragment.showLoadProgress();
    }

    @Override
    public void onAfter() {
        super.onAfter();
        diagnoseFragment.hideLoadProgress();
        diagnoseFragment.getNextQuestion();
    }

    @Override
    public void onSuccess(CheckLogicBean result) {
        super.onSuccess(result);

        CheckLogicBean.CheckLogicInfo chkInfo = result.getResult();
        if(result.getStatus() == 200 && chkInfo != null){

            diagnoseFragment.setDiagnoseCheckLogic(chkInfo);


            //String urlParam="?Code=" + chkInfo.getSkipCode();
            //if(chkInfo.getSkipType() == 0){

           //     Http.get(Url.URL_QUESTION_STEP + urlParam, listener);
            //}else if(chkInfo.getSkipType() == 1){
            //    Http.get(Url.URL_ANSWER_STEP + urlParam, listener);
            //}

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
