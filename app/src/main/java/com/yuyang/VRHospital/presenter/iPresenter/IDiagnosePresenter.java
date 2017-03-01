package com.yuyang.VRHospital.presenter.iPresenter;

import java.util.Map;

/**
 * Created by yuyang on 16/4/24.
 */
public interface IDiagnosePresenter {
    /**
     * 获取诊断所需内容
     * @param questionCode
     */
    void getDiagnoseContent(String questionCode);

    /**
     * 提交诊断内容
     * @param params
     */
    void submitDiagnoseContent(String questionCode, Map<String,String> params);

    /**
     * 提交诊断答案
     * @param answerCode
     */
    //void getDiagnoseResult(String answerCode);
}
