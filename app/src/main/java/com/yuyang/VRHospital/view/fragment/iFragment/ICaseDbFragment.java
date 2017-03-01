package com.yuyang.VRHospital.view.fragment.iFragment;

import com.yuyang.VRHospital.IBaseInterface;
import com.yuyang.VRHospital.bean.CaseBean;
import com.yuyang.VRHospital.bean.CaseItemBean;

import java.util.List;

/**
 * Created by yuyang on 16/4/21.
 */
public interface ICaseDbFragment extends IBaseInterface {
    /**
     * 加载更多
     * @param caseItemBeans
     */
    void addListData(List<CaseItemBean> caseItemBeans);

    /**
     * 第一次加载或者查询／刷新
     * @param caseItemBeans
     */
    void setListData(List<CaseItemBean> caseItemBeans);

    void setCanLoadMore(int state);
}
