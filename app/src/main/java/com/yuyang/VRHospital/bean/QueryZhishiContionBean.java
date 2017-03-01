package com.yuyang.VRHospital.bean;

import com.yuyang.VRHospital.bean.ConditionInfo;
/**
 * Created by fanshy on 2016/7/1.
 */
public class QueryZhishiContionBean {
    //{"pageIndex":1,"pageSize":10,"order":"desc","conditions":{"title":"发","keyWord":"岁"}}
    int             pageIndex;
    int             pageSize;
    String          order;
    ConditionInfo    conditions;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public ConditionInfo getConditions() {
        return conditions;
    }

    public void setConditions(ConditionInfo conditions) {
        this.conditions = conditions;
    }
}
