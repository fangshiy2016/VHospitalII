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
        private List<JianCeBean> jianCeXiangList;
        private List<ZhiLiaoBean> zhiLiaoXiangList;
        private KeshiBean keShiModel;
        private JiuYiTerm  jiuYiTermModel;
        private ZhiShiBean zhiShiModel;


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

        public KeshiBean getKeShiModel() {
            return keShiModel;
        }

        public void setKeShiModel(KeshiBean keShiModel) {
            this.keShiModel = keShiModel;
        }

        public JiuYiTerm getJiuYiTermModel() {
            return jiuYiTermModel;
        }

        public void setJiuYiTermModel(JiuYiTerm jiuYiTermModel) {
            this.jiuYiTermModel = jiuYiTermModel;
        }

        public ZhiShiBean getZhiShiModel() {
            return zhiShiModel;
        }

        public void setZhiShiModel(ZhiShiBean zhiShiModel) {
            this.zhiShiModel = zhiShiModel;
        }

        public List<JianCeBean> getJianCeXiangList() {
            return jianCeXiangList;
        }

        public void setJianCeXiangList(List<JianCeBean> jianCeXiangList) {
            this.jianCeXiangList = jianCeXiangList;
        }

        public List<ZhiLiaoBean> getZhiLiaoXiangList() {
            return zhiLiaoXiangList;
        }

        public void setZhiLiaoXiangList(List<ZhiLiaoBean> zhiLiaoXiangList) {
            this.zhiLiaoXiangList = zhiLiaoXiangList;
        }

    }
}
