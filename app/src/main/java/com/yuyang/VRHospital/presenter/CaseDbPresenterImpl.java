package com.yuyang.VRHospital.presenter;

import com.yuyang.VRHospital.bean.CaseBean;
import com.yuyang.VRHospital.bean.CaseItemBean;
import com.yuyang.VRHospital.common.Contants;
import com.yuyang.VRHospital.model.CaseDbModelImpl;
import com.yuyang.VRHospital.model.iModel.ICaseDbModel;
import com.yuyang.VRHospital.network.listener.CallbackListener;
import com.yuyang.VRHospital.presenter.iPresenter.ICaseDbPresenter;
import com.yuyang.VRHospital.view.fragment.iFragment.ICaseDbFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyang on 16/4/21.
 */
public class CaseDbPresenterImpl  implements ICaseDbPresenter {
    private int PAGE_SIZE = 15;
    private int page_count = 1;
    private int current_page = 1;

    private String condition;

    private ICaseDbFragment caseDbFragment;
    private ICaseDbModel caseDbModel;

    public CaseDbPresenterImpl(ICaseDbFragment caseDbFragment){
        caseDbModel = new CaseDbModelImpl(new LoadCaseCallBack());
        this.caseDbFragment = caseDbFragment;
    }

    @Override
    public void loadCaseData() {
        caseDbModel.loadCaseData(condition,current_page,PAGE_SIZE);
    }

    @Override
    public void setCurrentPage(int i) {
        current_page = i;
    }

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    class LoadCaseCallBack extends CallbackListener<CaseBean>{
        @Override
        public void onBefore() {
            super.onBefore();
            caseDbFragment.showLoadProgress();
        }

        @Override
        public void onAfter() {
            super.onAfter();
            caseDbFragment.hideLoadProgress();
        }

        @Override
        public void onSuccess(CaseBean result) {
            super.onSuccess(result);
            //TODO yuyang 页数通过返回值获取
            page_count      = result.getResult().getPageTotal();//2;
            current_page    = result.getResult().getPageIndex();

            if(current_page == 1 && result.getResult().getData() != null){
                caseDbFragment.setListData(result.getResult().getData());
            }else {
                if(result.getResult().getData() != null)caseDbFragment.addListData(result.getResult().getData());
            }
            if(current_page >= page_count){
                caseDbFragment.setCanLoadMore(Contants.NO_LOAD_MORE);
            }else {
                caseDbFragment.setCanLoadMore(Contants.CAN_LOAD_MORE);
            }
            current_page ++;
        }

        @Override
        public void onError(Exception e) {
            super.onError(e);
            /*caseDbFragment.showErrorDialog();
            if(current_page == 1) {
                caseDbFragment.setCanLoadMore(Contants.LOAD_FAIL);
            }*/
            onStringResult(null);
        }

        @Override
        public void onStringResult(String result) {
            super.onStringResult(result);
            //TODO yuyang 测试数据
            /*List<CaseBean> caseBeanList = new ArrayList<>();
            caseBeanList.add(new CaseBean("1","张三","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","李四","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","李四","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","李四","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","李四","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","李四","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","李四","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","李四","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","13","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","张三","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","李四","12","女","内科","2015-02-03"));
            caseBeanList.add(new CaseBean("1","李四","13","女","内科","2015-02-03"));
            onSuccess(caseBeanList);
            */
        }
    }

}
