package com.yuyang.VRHospital.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyang.VRHospital.BaseActivity;
import com.yuyang.VRHospital.IBaseInterface;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.CaseItemBean;
import com.yuyang.VRHospital.bean.ExplainItemBean;
import com.yuyang.VRHospital.bean.KeyItemBean;
import com.yuyang.VRHospital.bean.ResultItemBean;
import com.yuyang.VRHospital.presenter.ExplainPresenterImpl;
import com.yuyang.VRHospital.view.activity.iView.IDiagnoseExplainActivity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class CaseDetailActivity extends BaseActivity implements IDiagnoseExplainActivity {


    @Bind(R.id.case_detail_layout)
    LinearLayout mContentLayout;
    @Bind(R.id.case_detail_overview)
    TextView overview;


    CaseItemBean caseItem;
    @Override
    public void initLayoutId() {
        layoutId = R.layout.activity_case_detail;
    }

    @Override
    public void initViews() {
        //showBackAction(true);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_explain_item,null);
        TextView lable = (TextView) view.findViewById(R.id.explain_item_lable);
        //lable.setText("病历详情");
        getSupportActionBar().setTitle("病历详情");
        caseItem = new CaseItemBean();

        caseItem.setId(getIntent().getExtras().getInt("id"));
        caseItem.setCaseCode(getIntent().getExtras().getString("caseCode"));
        caseItem.setUserCode(getIntent().getExtras().getString("userCode"));
        caseItem.setUserCard(getIntent().getExtras().getString("userCard"));
        caseItem.setCreateData(new Date(getIntent().getExtras().getLong("createData")));
        caseItem.setDoctorName(getIntent().getExtras().getString("doctorName"));
        caseItem.setZdXinxi(getIntent().getExtras().getString("zdXinxi"));
        caseItem.setJieLun(getIntent().getExtras().getString("jieLun"));
        caseItem.setKeShi(getIntent().getExtras().getString("keShi"));
        caseItem.setJyJianyi(getIntent().getExtras().getString("jyJianyi"));
        caseItem.setSex(getIntent().getExtras().getInt("sex"));
        caseItem.setAge(getIntent().getExtras().getInt("age"));
        caseItem.setJieHunStatus(getIntent().getExtras().getInt("jieHunStatus"));
        caseItem.setShengYuStatus(getIntent().getExtras().getInt("shengYuStatus"));


        if(caseItem != null){

            List<KeyItemBean> itemBeans = new ArrayList<>();
            itemBeans.add(new KeyItemBean("病 例 号", caseItem.getCaseCode()));
            itemBeans.add(new KeyItemBean("用户编码", caseItem.getUserCode()));
            itemBeans.add(new KeyItemBean("就 诊 卡", caseItem.getUserCard()));
            itemBeans.add(new KeyItemBean("科    室", caseItem.getKeShi()));
            itemBeans.add(new KeyItemBean("年    龄", Integer.toString(caseItem.getAge())));
            itemBeans.add(new KeyItemBean("性    别", caseItem.getSex() == 0 ? "男":"女"));
            itemBeans.add(new KeyItemBean("就诊时间", caseItem.getCreateData().toString()));
            itemBeans.add(new KeyItemBean("医    生", caseItem.getDoctorName()));
            itemBeans.add(new KeyItemBean("诊断选项", caseItem.getZdXinxi()));
            itemBeans.add(new KeyItemBean("诊断结论", caseItem.getJieLun()));


            for (KeyItemBean itemBean:itemBeans){
                View itemView = LayoutInflater.from(this).inflate(R.layout.layout_result_info_item, null);
                TextView labledetail = (TextView) itemView.findViewById(R.id.result_item_lable);
                TextView content = (TextView) itemView.findViewById(R.id.result_item_content);
                labledetail.setText(getResources().getString(R.string.result_item_label, itemBean.getLable()));
                content.setText(Html.fromHtml(itemBean.getContent()));
                mContentLayout.addView(itemView);
            }
        }

    }

    @Override
    public void setOverview(String title, String content) {
    }

    @Override
    public void setItems(ExplainItemBean itemBean,int colorId) {
    }

    @Override
    public void showErrorDialog() {
        Toast.makeText(this, R.string.load_fail, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadProgress() {
        showProgressDialog();
    }

    @Override
    public void hideLoadProgress() {
        hideProgressDialog();
    }

}
