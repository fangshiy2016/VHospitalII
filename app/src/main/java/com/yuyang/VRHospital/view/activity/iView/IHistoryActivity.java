package com.yuyang.VRHospital.view.activity.iView;

import com.yuyang.VRHospital.IBaseInterface;
import com.yuyang.VRHospital.bean.HistoryItemBean;

import java.util.List;

/**
 * Created by yuyang on 2016/4/24.
 */
public interface IHistoryActivity extends IBaseInterface {
    void setHistoryContent(List<HistoryItemBean> itemBean);
}
