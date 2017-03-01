package com.yuyang.VRHospital.presenter;

import android.content.Context;
import android.widget.Toast;

import com.yuyang.VRHospital.bean.CaseItemBean;
import com.yuyang.VRHospital.bean.CheckLogicBean;
import com.yuyang.VRHospital.bean.RespnoseBean;
import com.yuyang.VRHospital.model.DiagnoseModelImpl;
import com.yuyang.VRHospital.model.iModel.IDiagnoseModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.ICaseSavePresenter;
import com.yuyang.VRHospital.view.activity.DiagnoseResultActivity;
import com.yuyang.VRHospital.view.activity.iView.IDiagnoseResultActivity;

/**
 * Created by fanshy on 2016/6/29.
 */
public class CaseSavePresenterImpl   extends CallbackListener<RespnoseBean> implements ICaseSavePresenter {

    private DiagnoseResultActivity resultActivity;
    private Context mContext;
    private IDiagnoseModel diagnoseModel;

    public CaseSavePresenterImpl(DiagnoseResultActivity iResultActivity){
        this.resultActivity = iResultActivity;
        this.mContext = iResultActivity;
        diagnoseModel = new DiagnoseModelImpl(this);
    }

    @Override
    public void saveCaseBean(CaseItemBean caseItem) {

        diagnoseModel.submitUserCase(caseItem);
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
    public void onSuccess(RespnoseBean result) {
        super.onSuccess(result);

        if(result.getStatus() == 200){

            Toast.makeText(mContext, result.getResult() , Toast.LENGTH_SHORT).show();
            resultActivity.disableSubmit();
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
