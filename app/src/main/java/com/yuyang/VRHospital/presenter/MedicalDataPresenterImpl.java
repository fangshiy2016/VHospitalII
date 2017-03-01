package com.yuyang.VRHospital.presenter;

import com.yuyang.VRHospital.bean.ConditionInfo;
import com.yuyang.VRHospital.bean.MedicalDataBean;
import com.yuyang.VRHospital.bean.MedicalDataByFolderBean;
import com.yuyang.VRHospital.bean.QueryZhishiContionBean;
import com.yuyang.VRHospital.common.Contants;
import com.yuyang.VRHospital.model.MedicalDataModelImpl;
import com.yuyang.VRHospital.model.iModel.IMedicalDataModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.IMedicalDataPresenter;
import com.yuyang.VRHospital.view.fragment.iFragment.IMedicalDataDbFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyang on 16/4/21.
 */
public class MedicalDataPresenterImpl implements IMedicalDataPresenter {
    private int PAGE_SIZE = 7;
    private int current_page = 1;
    private int page_count = 1;

    private IMedicalDataDbFragment fragment;
    private IMedicalDataModel model;

    /**
     *  查询条件请自行定义
     */
    private String condition;
    private String medicalClass;
    private String order = "desc";
    QueryZhishiContionBean mConditionBean = new QueryZhishiContionBean();
    public MedicalDataPresenterImpl(IMedicalDataDbFragment fragment){
        this.fragment = fragment;
        model = new MedicalDataModelImpl();
    }

    @Override
    public void loadMedicalData() {
        mConditionBean.setPageIndex(current_page);
        mConditionBean.setPageSize(PAGE_SIZE);
        //mConditionBean.setOrder("desc");
        if(condition != null) {
            ConditionInfo conditionInfo = new  ConditionInfo();
            conditionInfo.setKeyWord(condition);
            conditionInfo.setTitle(condition);
            mConditionBean.setConditions(conditionInfo);
        }
        model.loadMedical(mConditionBean, new LoadMedicalCallBack());
    }

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public void setMedicalClass(String medicalClass) {
        this.medicalClass = medicalClass;
    }

    @Override
    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public void setCurrentPage(int i) {
        current_page = i;
    }

    @Override
    public void resetAllConditionAndPage() {
        current_page = 1;
        condition = null;
        medicalClass = null;
        order = null;
    }

    @Override
    public void loadMedicalDataByFolderID(int FolderID){
        model.loadMedicalByFolderID(FolderID, new LoadMedicalByFolderCallBack());
    }

    class LoadMedicalCallBack extends CallbackListener<MedicalDataBean> {
        @Override
        public void onBefore() {
            super.onBefore();
            if (current_page == 1) {
                fragment.showLoadProgress();
            }
        }

        @Override
        public void onAfter() {
            super.onAfter();
            fragment.setQueryReturn();
            fragment.hideLoadProgress();
        }

        @Override
        public void onSuccess(MedicalDataBean result) {
            super.onSuccess(result);
            //TODO yuyang 页数通过返回值获取
            if(result.getStatus() != 200 || result.getResult() == null) return ;

            page_count   = result.getResult().getPageTotal();
            current_page = result.getResult().getPageIndex();
            if(current_page == 1){
                fragment.setListData(result.getResult().getData());
            }else {
                fragment.addListData(result.getResult().getData());
            }
            if(current_page >= page_count){
                fragment.setCanLoadMore(Contants.NO_LOAD_MORE);
            }else {
                fragment.setCanLoadMore(Contants.CAN_LOAD_MORE);
            }
            current_page ++;
        }

        @Override
        public void onError(Exception e) {
            super.onError(e);
            /*fragment.showErrorDialog();
            if(current_page == 1){
                fragment.setCanLoadMore(Contants.LOAD_FAIL);
            }*/
            fragment.setQueryReturn();
            onStringResult(null);
        }

        @Override
        public void onStringResult(String result) {
            /*
            super.onStringResult(result);
            List<MedicalDataBean> dataBeans = new ArrayList<>();
            dataBeans.add(new MedicalDataBean("预防从小孩抓起","2016-01-04"));
            dataBeans.add(new MedicalDataBean("何时资格考试一句话考点总结","2015-11-04"));
            dataBeans.add(new MedicalDataBean("中山大学 细胞生物学动画（一共26个）","2016-01-04"));
            dataBeans.add(new MedicalDataBean("神经外科手术精髓＋3D模拟手术入路","2016-01-04"));
            dataBeans.add(new MedicalDataBean("儿童食物过敏的诊断和治疗", "2016-02-04"));
            dataBeans.add(new MedicalDataBean("纤维支气管镜辅助气管插管术", "2015-01-04"));
            dataBeans.add(new MedicalDataBean("预防从小孩抓起","2016-01-04"));
            dataBeans.add(new MedicalDataBean("何时资格考试一句话考点总结","2015-11-04"));
            dataBeans.add(new MedicalDataBean("中山大学 细胞生物学动画（一共26个）","2016-01-04"));
            dataBeans.add(new MedicalDataBean("神经外科手术精髓＋3D模拟手术入路","2016-01-04"));
            dataBeans.add(new MedicalDataBean("儿童食物过敏的诊断和治疗", "2016-02-04"));
            dataBeans.add(new MedicalDataBean("纤维支气管镜辅助气管插管术","2015-01-04"));
            dataBeans.add(new MedicalDataBean("预防从小孩抓起","2016-01-04"));
            dataBeans.add(new MedicalDataBean("何时资格考试一句话考点总结","2015-11-04"));
            dataBeans.add(new MedicalDataBean("中山大学 细胞生物学动画（一共26个）","2016-01-04"));
            dataBeans.add(new MedicalDataBean("神经外科手术精髓＋3D模拟手术入路","2016-01-04"));
            dataBeans.add(new MedicalDataBean("儿童食物过敏的诊断和治疗", "2016-02-04"));
            dataBeans.add(new MedicalDataBean("纤维支气管镜辅助气管插管术","2015-01-04"));
            onSuccess(dataBeans);
            */
        }
    }

    class LoadMedicalByFolderCallBack extends CallbackListener<MedicalDataByFolderBean> {
        @Override
        public void onBefore() {
            super.onBefore();
            if (current_page == 1) {
                fragment.showLoadProgress();
            }
        }

        @Override
        public void onAfter() {
            super.onAfter();
            fragment.setQueryReturn();
            fragment.hideLoadProgress();
        }

        @Override
        public void onSuccess(MedicalDataByFolderBean result) {
            super.onSuccess(result);
            //TODO yuyang 页数通过返回值获取
            if(result.getStatus() != 200) return ;

            fragment.setListData(result.getResult());
            fragment.setCanLoadMore(Contants.NO_LOAD_MORE);
            current_page++;
        }

        @Override
        public void onError(Exception e) {
            super.onError(e);
            /*fragment.showErrorDialog();
            if(current_page == 1){
                fragment.setCanLoadMore(Contants.LOAD_FAIL);
            }*/
            onStringResult(null);
            fragment.setQueryReturn();
        }

        @Override
        public void onStringResult(String result) {
        }
    }
}
