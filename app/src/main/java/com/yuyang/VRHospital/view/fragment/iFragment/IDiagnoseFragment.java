package com.yuyang.VRHospital.view.fragment.iFragment;

import com.yuyang.VRHospital.IBaseInterface;
import com.yuyang.VRHospital.bean.CheckLogicBean;
import com.yuyang.VRHospital.bean.DiagnoseItemBean;

import java.util.List;
import java.util.Map;

/**
 * Created by yuyang on 16/4/24.
 */
public interface IDiagnoseFragment extends IBaseInterface {
    void setDiagnoseContent(List<DiagnoseItemBean> itemBeans);

    Map<String, String> getSubmitContent();

    void setDiagnoseCheckLogic(CheckLogicBean.CheckLogicInfo loginInfo);

    void getNextQuestion();
}
