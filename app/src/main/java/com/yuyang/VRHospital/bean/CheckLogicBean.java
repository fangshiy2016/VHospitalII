package com.yuyang.VRHospital.bean;

import com.yuyang.VRHospital.BaseBean;

/**
 * Created by fanshy on 2016/6/21.
 */
public class CheckLogicBean extends BaseBean {

    private CheckLogicInfo result;



    public CheckLogicInfo getResult() {
        return result;
    }
    public void setResult(CheckLogicInfo result) {
        this.result = result;
    }


    public class CheckLogicInfo {
        int skipType;
        String logic;
        String skipCode;

        public int getSkipType() {
            return skipType;
        }

        public void setSkipType(int skipType) {
            this.skipType = skipType;
        }

        public String getSkipCode() {
            return skipCode;
        }

        public void setSkipCode(String skipCode) {
            this.skipCode = skipCode;
        }

        public String getLogic() {
            return logic;
        }

        public void setLogic(String logic) {
            this.logic = logic;
        }
    }
}
