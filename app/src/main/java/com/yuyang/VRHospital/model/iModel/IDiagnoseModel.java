package com.yuyang.VRHospital.model.iModel;

import com.yuyang.VRHospital.bean.CaseItemBean;

import java.util.Map;

/**
 * Created by yuyang on 16/4/24.
 */
public interface IDiagnoseModel {
    void loadDiagnoseContent(String tag);

    void submitDiagnoseContent(String questionCode, Map<String, String> params);

    void getDiagnoseResult(String tag);

    void submitUserCase(CaseItemBean caseBean);
}
