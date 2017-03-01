package com.yuyang.VRHospital.bean;

import com.yuyang.VRHospital.BaseBean;

import java.io.Serializable;
import java.util.List;
/**
 * Created by yuyang on 16/4/23.
 * 诊断结果
 */
public class ResultBean  extends BaseBean implements Serializable{

    private ResultInfo result;
    public ResultInfo getResult() {
        return result;
    }
    public void setResult(ResultInfo result) {
        this.result = result;
    }
    public class ResultInfo{
        private int    floderID;
        private String code;
        private String title;
        private String content;

        //结构部分
        private List<JianCeInfo> jianCeXiangList;
        private List<ZhiLiaoInfo> zhiLiaoXiangList;
        private KeshiInfo  keShiModel;
        private JiuYiTerm  jiuYiTermModel;
        private ZhiShiInfo zhiShiModel;


        //private String jiuYiTerm;
        //private String keShi;
        //private String jianCeXiang;
        //private String zhiLiaoXiang;
        //private String zhiShiCode;

        public int getFloderID() {
            return floderID;
        }

        public void setFloderID(int floderID) {
            this.floderID = floderID;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public KeshiInfo getKeShiModel() {
            return keShiModel;
        }

        public void setKeShiModel(KeshiInfo keShiModel) {
            this.keShiModel = keShiModel;
        }

        public JiuYiTerm getJiuYiTermModel() {
            return jiuYiTermModel;
        }

        public void setJiuYiTermModel(JiuYiTerm jiuYiTermModel) {
            this.jiuYiTermModel = jiuYiTermModel;
        }

        public ZhiShiInfo getZhiShiModel() {
            return zhiShiModel;
        }

        public void setZhiShiModel(ZhiShiInfo zhiShiModel) {
            this.zhiShiModel = zhiShiModel;
        }

        public List<JianCeInfo> getJianCeXiangList() {
            return jianCeXiangList;
        }

        public void setJianCeXiangList(List<JianCeInfo> jianCeXiangList) {
            this.jianCeXiangList = jianCeXiangList;
        }

        public List<ZhiLiaoInfo> getZhiLiaoXiangList() {
            return zhiLiaoXiangList;
        }

        public void setZhiLiaoXiangList(List<ZhiLiaoInfo> zhiLiaoXiangList) {
            this.zhiLiaoXiangList = zhiLiaoXiangList;
        }

        /*
        public String getJiuYiTerm() {
            return jiuYiTerm;
        }

        public void setJiuYiTerm(String jiuYiTerm) {
            this.jiuYiTerm = jiuYiTerm;
        }

        public String getKeShi() {
            return keShi;
        }

        public void setKeShi(String keShi) {
            this.keShi = keShi;
        }

        public String getJianCeXiang() {
            return jianCeXiang;
        }

        public void setJianCeXiang(String jianCeXiang) {
            this.jianCeXiang = jianCeXiang;
        }

        public String getZhiLiaoXiang() {
            return zhiLiaoXiang;
        }

        public void setZhiLiaoXiang(String zhiLiaoXiang) {
            this.zhiLiaoXiang = zhiLiaoXiang;
        }

        public String getZhiShiCode() {
            return zhiShiCode;
        }

        public void setZhiShiCode(String zhiShiCode) {
            this.zhiShiCode = zhiShiCode;
        }
        */
    }
    /*
    private String sex;
    private String age;
    private String marriage_state;
    private String qr_code;
    private String code;
    private String doctor_advice;
    private List<ResultItemBean> items;

    public ResultBean() {
    }

    public ResultBean(String sex, String age, String marriage_state, String qr_code, String code, String doctor_advice, List<ResultItemBean> items) {
        this.sex = sex;
        this.age = age;
        this.marriage_state = marriage_state;
        this.qr_code = qr_code;
        this.code = code;
        this.doctor_advice = doctor_advice;
        this.items = items;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMarriage_state() {
        return marriage_state;
    }

    public void setMarriage_state(String marriage_state) {
        this.marriage_state = marriage_state;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDoctor_advice() {
        return doctor_advice;
    }

    public void setDoctor_advice(String doctor_advice) {
        this.doctor_advice = doctor_advice;
    }

    public List<ResultItemBean> getItems() {
        return items;
    }

    public void setItems(List<ResultItemBean> items) {
        this.items = items;
    }
    */
}
