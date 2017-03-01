package com.yuyang.VRHospital.bean;

import com.yuyang.VRHospital.BaseBean;

import java.sql.Date;
import java.util.List;

/**
 * Created by yuyang on 16/4/21.
 * 病历
 */
public class CaseBean extends BaseBean {

    //
    private CaseResponseInfo result;
    public CaseResponseInfo getResult() {
        return result;
    }

    public void setResult(CaseResponseInfo result) {
        this.result = result;
    }

    public class CaseResponseInfo {
        //{"pageIndex":1,"pageTotal":1,"pageSize":10,"recordTotal":9,"data":
        // [{"id":1,"caseCode":"1111","userCode":"admin","userCard":"admin","createData":"2016-06-19T11:51:05",
        // "doctorName":"admin","zdXinxi":"HPVJL1","jieLun":"","keShi":"","jyJianyi":"","jkJianyi":"","sex":0,
        // "age":0,"jieHunStatus":0,"shengYuStatus":0},

        int pageIndex;
        int pageTotal;
        int pageSize;
        int recordTotal;
        List<CaseItemBean> data;

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

        public List<CaseItemBean> getData() {
            return data;
        }

        public void setData(List<CaseItemBean> data) {
            this.data = data;
        }
    }



    /*private String id;
    private String name;
    private String age;
    private String sex;
    private String classify;
    private String time;

    public CaseBean() {
    }

    public CaseBean(String id, String name, String age, String sex, String classify, String time) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.classify = classify;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    */
}
