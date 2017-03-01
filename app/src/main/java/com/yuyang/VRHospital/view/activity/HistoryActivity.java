package com.yuyang.VRHospital.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yuyang.VRHospital.BaseActivity;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.HistoryItemBean;
import com.yuyang.VRHospital.presenter.HistoryPresenterImpl;
import com.yuyang.VRHospital.presenter.iPresenter.IHistoryPresenter;
import com.yuyang.VRHospital.view.activity.iView.IHistoryActivity;
import com.yuyang.VRHospital.view.form.DynamicFormHelper;

import java.util.List;

import butterknife.Bind;

/**
 * Created by yuyang on 16/4/24.
 * 往期追逆
 */
public class HistoryActivity extends BaseActivity implements IHistoryActivity {
    public static final String TAG = "tag";

    @Bind(R.id.history_add)
    Button add;
    @Bind(R.id.history_cancel)
    Button cancel;
    @Bind(R.id.history_item_layout)
    LinearLayout itemLayout;

    private DynamicFormHelper formHelper;
    private IHistoryPresenter historyPresenter;

    @Override
    public void initLayoutId() {
        layoutId = R.layout.activity_history;
    }

    @Override
    public void initViews() {
        titleView.setText(R.string.history_title);
        showBackAction(true);

        historyPresenter = new HistoryPresenterImpl(this);
        String tag = getIntent().getStringExtra(TAG);
        historyPresenter.getHistoryContent(tag);

        formHelper = new DynamicFormHelper(this,itemLayout,R.layout.layout_history_item);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO yuyang 这里不知道要干什么
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setHistoryContent(List<HistoryItemBean> itemBeans) {
        formHelper.parseHistoryShow(itemBeans);
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
