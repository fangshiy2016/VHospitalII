package com.yuyang.VRHospital.presenter.iPresenter;

/**
 * Created by yuyang on 16/4/21.
 */
public interface IMedicalDataPresenter {
    void loadMedicalData();
    void setCurrentPage(int i);
    void setCondition(String condition);
    void setMedicalClass(String medicalClass);
    void setOrder(String order);
    void resetAllConditionAndPage();

    void loadMedicalDataByFolderID(int FolderID);
}
