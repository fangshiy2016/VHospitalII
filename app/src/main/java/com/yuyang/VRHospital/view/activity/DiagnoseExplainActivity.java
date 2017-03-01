package com.yuyang.VRHospital.view.activity;

import android.text.Html;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyang.VRHospital.BaseActivity;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.ExplainItemBean;
import com.yuyang.VRHospital.presenter.ExplainPresenterImpl;
import com.yuyang.VRHospital.presenter.iPresenter.IExplainPresenter;
import com.yuyang.VRHospital.view.activity.iView.IDiagnoseExplainActivity;

import butterknife.Bind;

/**
 * Created by yuyang on 16/4/20.
 * 诊断属性的解释
 */
public class DiagnoseExplainActivity extends BaseActivity implements IDiagnoseExplainActivity {
    public static final String TITLE = "title";
    public static final String LABLE = "lable";

    @Bind(R.id.explain_item_layout)
    LinearLayout itemContentLayout;

    @Bind(R.id.explain_title)
    TextView titleView;
    @Bind(R.id.explain_overview)
    TextView overview;

    private IExplainPresenter explainPresenter;

    @Override
    public void initLayoutId() {
        layoutId = R.layout.activity_diagnose_explain;
    }

    @Override
    public void initViews() {
        //showBackAction(true);
        String title = getIntent().getExtras().getString(TITLE);
        String lable = getIntent().getExtras().getString(LABLE);
        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(lable)){
            //Toast.makeText(this,"title不能为空",Toast.LENGTH_SHORT).show();
        }else {
            setOverview(title, lable);
        }

        explainPresenter = new ExplainPresenterImpl(this);
        explainPresenter.loadExplain(lable);

        TextPaint tp = titleView.getPaint();
        tp.setFakeBoldText(true);

    }

    @Override
    public void setOverview(String title, String content) {
        titleView.setText(title);

        getSupportActionBar().setTitle("知识详情");
        if(content != null){
            CharSequence charSequence = Html.fromHtml(content);
            overview.setText(charSequence);//getString(R.string.explain_overview, content));
        }
    }

    @Override
    public void setItems(ExplainItemBean itemBean,int colorId) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_explain_item,null);
        view.setBackgroundResource(colorId);
        TextView lable = (TextView) view.findViewById(R.id.explain_item_lable);
        TextView content = (TextView) view.findViewById(R.id.explain_item_content);
        lable.setText(getResources().getString(R.string.item_lable, itemBean.getLable()));
        content.setText(itemBean.getContent());
        itemContentLayout.addView(view);
    }

    @Override
    public void showErrorDialog() {
        Toast.makeText(this,R.string.load_fail,Toast.LENGTH_LONG).show();
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
