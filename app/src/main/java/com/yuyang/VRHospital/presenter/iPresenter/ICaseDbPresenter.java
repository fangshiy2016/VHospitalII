package com.yuyang.VRHospital.presenter.iPresenter;

/**
 * Created by yuyang on 16/4/21.
 */
public interface ICaseDbPresenter {
    void loadCaseData();

    void setCurrentPage(int i);

    void setCondition(String condition);
}
