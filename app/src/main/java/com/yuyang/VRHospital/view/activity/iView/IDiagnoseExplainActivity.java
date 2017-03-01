package com.yuyang.VRHospital.view.activity.iView;

import com.yuyang.VRHospital.IBaseInterface;
import com.yuyang.VRHospital.bean.ExplainItemBean;

/**
 * Created by yuyang on 16/4/20.
 */
public interface IDiagnoseExplainActivity extends IBaseInterface {
    /**
     * 设置概述
     * @param overview
     */
    void setOverview(String title, String overview);

    /**
     * 设置概述上方的items
     * @param itemBean
     */
    void setItems(ExplainItemBean itemBean, int colorId);
}
