package com.yuyang.VRHospital.view.fragment.iFragment;

import com.yuyang.VRHospital.IBaseInterface;
import com.yuyang.VRHospital.bean.MedicalDataBean;
import com.yuyang.VRHospital.bean.ZhiShiInfo;

import java.util.List;

/**
 * Created by yuyang on 16/4/21.
 */
public interface IMedicalDataDbFragment extends IBaseInterface {
    void setCanLoadMore(int state);

    void setListData(List<ZhiShiInfo> result);

    void addListData(List<ZhiShiInfo> result);

    void setQueryReturn();
}
