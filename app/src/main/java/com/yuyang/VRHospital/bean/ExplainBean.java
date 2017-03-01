package com.yuyang.VRHospital.bean;

import com.yuyang.VRHospital.BaseBean;

import java.util.List;

/**
 * Created by yuyang on 16/4/20.
 * 病理单条解释
 */
 /* 返回json格式：
        {
        "itemBeans": [
        {
        "content": "内容内容1",
        "lable": "标签1"
        },
        {
        "content": "内容内容2",
        "lable": "标签2"
        },
        {
        "content": "内容内容3",
        "lable": "标签3"
        }
        ],
        "overview": "概述内容概述内容概述内容概述内容概述内容概述内容概述内容。"
        }
        //实际是这样的
        {"status":200,"result":{"code":"ZS-001","floderID":81,"title":"为什么要生病","keyWord":"生病","vipCotent":"因为经常不锻炼，而且睡眠饮食都不好","userContent":"多吃饭","dtContent":"不锻炼"}}

*/
public class ExplainBean extends BaseBean {

    private ExplainInfo result;
    public ExplainInfo getResult() {
        return result;
    }

    public void setResult(ExplainInfo result) {
        this.result = result;
    }
    public class ExplainInfo{
        String  code;
        int     folderID;
        String  title;
        String  keyWord;
        String  vipCotent;
        String  userContent;
        String  dtContent;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getFolderID() {
            return folderID;
        }

        public void setFolderID(int folderID) {
            this.folderID = folderID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }

        public String getVipCotent() {
            return vipCotent;
        }

        public void setVipCotent(String vipCotent) {
            this.vipCotent = vipCotent;
        }

        public String getUserContent() {
            return userContent;
        }

        public void setUserContent(String userContent) {
            this.userContent = userContent;
        }

        public String getDtContent() {
            return dtContent;
        }

        public void setDtContent(String dtContent) {
            this.dtContent = dtContent;
        }
    }
    /*private List<ExplainItemBean> itemBeans;
    private String overview;

    public ExplainBean() {
    }

    public ExplainBean(List<ExplainItemBean> itemBeans, String overview) {
        this.itemBeans = itemBeans;
        this.overview = overview;
    }

    public List<ExplainItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(List<ExplainItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    */
}
