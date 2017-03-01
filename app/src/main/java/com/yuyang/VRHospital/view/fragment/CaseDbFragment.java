package com.yuyang.VRHospital.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyang.VRHospital.BaseFragment;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.CaseBean;
import com.yuyang.VRHospital.bean.CaseItemBean;
import com.yuyang.VRHospital.common.Contants;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.presenter.CaseDbPresenterImpl;
import com.yuyang.VRHospital.presenter.iPresenter.ICaseDbPresenter;
import com.yuyang.VRHospital.utils.DialogUtil;
import com.yuyang.VRHospital.view.activity.CaseDetailActivity;
import com.yuyang.VRHospital.view.activity.MipcaActivityCapture;
import com.yuyang.VRHospital.view.adapter.CaseTableDataAdapter;
import com.yuyang.VRHospital.view.fragment.iFragment.ICaseDbFragment;
import com.yuyang.VRHospital.view.widget.SortableCaseTableView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.codecrafters.tableview.listeners.TableDataClickListener;

/**
 * Created by yuyang on 16/4/21.
 * 病历库
 */
public class CaseDbFragment extends BaseFragment implements ICaseDbFragment, SwipeRefreshLayout.OnRefreshListener {

    private final static int SCANNIN_GREQUEST_CODE = 1;

    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.case_table_view)
    SortableCaseTableView tableView;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.toolbar_find)
    ImageView find;

    @Bind(R.id.toolbar_scan)
    ImageView qrScan;

    @Bind(R.id.mask)
    View maskView;

    private ImageView toFind;
    private EditText conditionEdit;

    private CaseTableDataAdapter adapter;
    private List<CaseItemBean> caseItemBeanList;
    private ICaseDbPresenter caseDbPresenter;

    private Dialog conditionDialog;
    private long       mRefeshCount;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_case_db;
    }

    @Override
    protected void initViews() {
        title.setText(R.string.case_title);
        find.setVisibility(View.VISIBLE);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionDialog.show();
                maskView.setVisibility(View.VISIBLE);
            }
        });
        mRefeshCount = 0;
        qrScan.setVisibility(View.VISIBLE);
        qrScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//二维码扫描 这里进行

                Intent intent = new Intent();
                intent.setClass(getContext(), MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });

        caseItemBeanList = new ArrayList<>();
        adapter = new CaseTableDataAdapter(getActivity(), caseItemBeanList);
        tableView.setDataAdapter(adapter);

        tableView.addDataClickListener(new CaseClickListener());
        tableView.getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1 && adapter.getLoadState() == Contants.CAN_LOAD_MORE) {
                        //if(mRefeshCount > 0) return;
                        //synchronized (this) {
                        //    mRefeshCount++;
                        //}
                        caseDbPresenter.loadCaseData();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        refreshLayout = tableView.getRefreshLayout();
        refreshLayout.setOnRefreshListener(this);

        caseDbPresenter = new CaseDbPresenterImpl(this);

        initAllDialog();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    //显示扫描到的内容
                    //Toast.makeText(getActivity(), "扫描内容：" +bundle.getString("result"), Toast.LENGTH_LONG).show();
                    String userCode = bundle.getString("result");

                    if(userCode.length() > Url.WEIXIN_URI_RELEASE.length())
                    {
                        userCode = userCode.substring(Url.WEIXIN_URI_RELEASE.length());//临时改
                    }

                    caseDbPresenter.setCondition(userCode);
                    caseDbPresenter.setCurrentPage(1);
                    caseDbPresenter.loadCaseData();
                }
                break;
        }
    }

    private void initAllDialog() {
        //初始化右上角查询弹窗
        View viewContent = LayoutInflater.from(getActivity()).inflate(R.layout.popup_window_layout, null);
        conditionDialog = new Dialog(getActivity(), R.style.dialogStyle);
        conditionDialog.setContentView(viewContent);
        conditionDialog.setCanceledOnTouchOutside(false);
        DialogUtil.setFullScreenWidth(getActivity(), conditionDialog);
        conditionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                maskView.setVisibility(View.GONE);
            }
        });
        toFind = (ImageView) viewContent.findViewById(R.id.popup_find);
        conditionEdit = (EditText) viewContent.findViewById(R.id.popup_condition);
        conditionEdit.setHint("输入用户编码");

        //设置条件查询并刷新页面
        toFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseDbPresenter.setCondition(conditionEdit.getText().toString());
                caseDbPresenter.setCurrentPage(1);
                caseDbPresenter.loadCaseData();
                conditionDialog.hide();
                maskView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void addListData(List<CaseItemBean> caseItemsBeans) {
        if(caseItemsBeans == null) return ;
        caseItemBeanList.addAll(caseItemsBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setListData(List<CaseItemBean> caseItemsBeans) {
        if(caseItemsBeans == null) return ;
        caseItemBeanList.clear();
        caseItemBeanList.addAll(caseItemsBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setCanLoadMore(int state) {
        if(Contants.NO_LOAD_MORE == state) {
            caseDbPresenter.setCondition("");
        }
        adapter.setLoadState(state);
    }

    @Override
    public void showErrorDialog() {
        Toast.makeText(getActivity(), R.string.load_fail, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadProgress() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoadProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        //if(mRefeshCount > 0) return;
        //synchronized (this) {
        //    mRefeshCount++;
        //}
        caseDbPresenter.setCurrentPage(1);
        caseDbPresenter.loadCaseData();
    }

    private class CaseClickListener implements TableDataClickListener<CaseItemBean> {
        @Override
        public void onDataClicked(int rowIndex, CaseItemBean clickedCase) {

            Intent intent = new Intent(getActivity(), CaseDetailActivity.class);

            Bundle b = new Bundle();
            b.putInt("id", clickedCase.getId());
            b.putString("caseCode", clickedCase.getCaseCode());
            b.putString("userCode", clickedCase.getUserCode());
            b.putString("userCard", clickedCase.getUserCard());
            b.putLong("createData", clickedCase.getCreateData().getTime());
            b.putString("doctorName", clickedCase.getDoctorName());
            b.putString("zdXinxi", clickedCase.getZdXinxi());
            b.putString("jieLun", clickedCase.getJieLun());
            b.putString("keShi", clickedCase.getKeShi());
            b.putString("jyJianyi", clickedCase.getJyJianyi());
            b.putInt("sex", clickedCase.getSex());
            b.putInt("age", clickedCase.getAge());
            b.putInt("jieHunStatus", clickedCase.getJieHunStatus());
            b.putInt("shengYuStatus", clickedCase.getShengYuStatus());

            intent.putExtras(b);

            startActivity(intent);
            //String clickedCarString = clickedCase.getCaseCode() + " " + clickedCase.getDoctorName();
            //Toast.makeText(getContext(), clickedCarString, Toast.LENGTH_SHORT).show();
        }
    }
}
