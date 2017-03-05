package com.yuyang.VRHospital.bean;

import com.yuyang.VRHospital.BaseBean;

import java.util.List;

/**
 * Created by yuyang on 16/4/21.
 * 医学资料
 */
public class MedicalDataBean extends BaseBean {

    private MdcResponseInfo result;
    public MdcResponseInfo getResult() {
        return result;
    }
    public void setResult(MdcResponseInfo result) {
        this.result = result;
    }

    public class MdcResponseInfo{
    //{"pageIndex":1,"pageTotal":3,"pageSize":10,"recordTotal":24,"data"
        int    pageIndex;
        int    pageTotal;
        int    pageSize;
        int    recordTotal;
        List<ZhiShiBean> data;

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getRecordTotal() {
            return recordTotal;
        }

        public void setRecordTotal(int recordTotal) {
            this.recordTotal = recordTotal;
        }

        public List<ZhiShiBean> getData() {
            return data;
        }

        public void setData(List<ZhiShiBean> data) {
            this.data = data;
        }
    }


    /*private String name;
    private String time;

    public MedicalDataBean() {
    }

    public MedicalDataBean(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    */
}
