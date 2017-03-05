package com.yuyang.VRHospital.view.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyang.VRHospital.BaseFragment;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.ZhiShiBean;
import com.yuyang.VRHospital.common.Contants;
import com.yuyang.VRHospital.presenter.MedicalDataPresenterImpl;
import com.yuyang.VRHospital.presenter.iPresenter.IMedicalDataPresenter;
import com.yuyang.VRHospital.utils.DialogUtil;
import com.yuyang.VRHospital.view.activity.DiagnoseExplainActivity;
import com.yuyang.VRHospital.view.adapter.DialogAdapter;
import com.yuyang.VRHospital.view.adapter.MedicalDataAdapter;
import com.yuyang.VRHospital.view.fragment.iFragment.IMedicalDataDbFragment;
import com.yuyang.VRHospital.view.widget.ListDialog;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by yuyang on 16/4/21.
 * 医学资料库
 */
public class MedicalDataDbFragment extends BaseFragment  implements SwipeRefreshLayout.OnRefreshListener,IMedicalDataDbFragment {

    @Bind(R.id.toolbar_find)
    ImageView find;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.medical_recycler)
    RecyclerView recyclerView;
    @Bind(R.id.medical_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.mask)
    View maskView;
    @Bind(R.id.spinner_class)
    Spinner mSpinnerClass;
    private ArrayAdapter<String> spinnerAdapter;
    private String[] mClassItems;
   // @Bind(R.id.medical_table_title_left)
   // TextView leftView;
   // @Bind(R.id.medical_table_title_right)
   // TextView rightView;

    private ImageView toFind;
    private EditText conditionEdit;

    private MedicalDataAdapter adapter;
    private LinearLayoutManager layoutManager;
    private IMedicalDataPresenter presenter;

    private Dialog conditionDialog;
    private ListDialog classDialog;
    private ListDialog timeOrderDialo;
    private long       mRefeshCount;
    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_medical_data;
    }

    @Override
    protected void initViews() {
        mRefeshCount = 0;
        presenter = new MedicalDataPresenterImpl(this);

        //初始化界面，添加点击事件等
        title.setText(R.string.medical_title);
        //leftView.setText(R.string.medical_table_title_left);
        //rightView.setText(R.string.medical_table_title_right);
        find.setVisibility(View.VISIBLE);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionDialog.show();
                maskView.setVisibility(View.VISIBLE);
            }
        });

        mClassItems = getResources().getStringArray(R.array.medical_class);
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mClassItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerClass.setAdapter(spinnerAdapter);
        mSpinnerClass.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                //spinnerAdapter.getItem(arg2);
                int folderID = 0;
                presenter.setMedicalClass(spinnerAdapter.getItem(arg2));
                presenter.setCurrentPage(1);
                switch (arg2) {
                    case 0:
                        if(mRefeshCount > 0) return;
                        synchronized (this) {
                            mRefeshCount++;
                        }
                        onRefresh();
                        break;
                    case 1:
                        if(mRefeshCount > 0) return;
                        synchronized (this) {
                            mRefeshCount++;
                        }
                        folderID = 120;
                        presenter.loadMedicalDataByFolderID(folderID);
                        break;//写死 为通过验收
                    case 2:
                        if(mRefeshCount > 0) return;
                        synchronized (this) {
                            mRefeshCount++;
                        }
                        folderID = 154;
                        presenter.loadMedicalDataByFolderID(folderID);
                        break;
                    case 3:
                        if(mRefeshCount > 0) return;
                        synchronized (this) {
                            mRefeshCount++;
                        }
                        folderID = 149;
                        presenter.loadMedicalDataByFolderID(folderID);
                        break;
                    default:
                        break;
                }
                arg0.setVisibility(View.VISIBLE);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                arg0.setVisibility(View.VISIBLE);
            }
        });

        refreshLayout.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);//
        adapter = new MedicalDataAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MedicalDataAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, ZhiShiBean dataBean) {

                //Toast.makeText(getActivity(), dataBean.getTitle(), 600).show();
                Intent intent = new Intent(getActivity(), DiagnoseExplainActivity.class);
                Bundle b = new Bundle();
                b.putString(DiagnoseExplainActivity.TITLE, dataBean.getTitle());
                b.putString(DiagnoseExplainActivity.LABLE, dataBean.getVipCotent());//dataBean.getDtContent());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (layoutManager.findLastVisibleItemPosition() == adapter.getItemCount() - 1
                            && adapter.getLoadState() == Contants.CAN_LOAD_MORE) {
                        //上拉加载更多
                        if(mRefeshCount > 0) return;
                        synchronized (this) {
                            mRefeshCount++;
                        }

                        presenter.loadMedicalData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                refreshLayout.setEnabled(layoutManager
                        .findFirstCompletelyVisibleItemPosition() == 0);
            }
        });
        /*
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Window dialogWindow = classDialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
                lp.x = leftView.getLeft(); // 新位置X坐标
                lp.y = leftView.getBottom()*3; // 新位置Y坐标
                dialogWindow.setAttributes(lp);
                classDialog.show();



            }
        });
        rightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //timeOrderDialo.show();
            }
        });
        */
        initAllDialog();
    }

    private void initAllDialog() {
        //初始化右上角查询弹窗
        View viewContent = LayoutInflater.from(getActivity()).inflate(R.layout.popup_window_layout,null);


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
        conditionEdit.setHint("输入标题或关键词");

        //设置条件查询并刷新页面
        toFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRefeshCount > 0) return;
                synchronized (this) {
                    mRefeshCount++;
                }

                presenter.setCondition(conditionEdit.getText().toString());
                presenter.setCurrentPage(1);
                presenter.loadMedicalData();
                conditionDialog.hide();
                maskView.setVisibility(View.GONE);
            }
        });

        //分类弹出框初始化
        classDialog = new ListDialog(getActivity(),"分类");
        List<String> classStrs = Arrays.asList(getResources().getStringArray(R.array.medical_class));
        final DialogAdapter classAdapter = new DialogAdapter(getActivity(), classStrs);
        classDialog.setAdapter(classAdapter);
        classDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int folderID = 0;
                presenter.setMedicalClass(classAdapter.getItem(position));
                presenter.setCurrentPage(1);
                switch (position){
                    case 0: folderID =120; break;//写死 为通过验收
                    case 1: folderID =154; break;
                    case 2:folderID =149; break;
                    default:break;
                }

                presenter.loadMedicalDataByFolderID(folderID);
                classDialog.hide();
            }
        });

        //分类弹出框初始化
        timeOrderDialo = new ListDialog(getActivity(),"排序");
        List<String> orderStrs = Arrays.asList(getResources().getStringArray(R.array.order));
        final DialogAdapter orderAdapter = new DialogAdapter(getActivity(), orderStrs);
        timeOrderDialo.setAdapter(orderAdapter);
        timeOrderDialo.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mRefeshCount > 0) return;
                synchronized (this) {
                    mRefeshCount++;
                }

                presenter.setOrder(orderAdapter.getItem(position));
                presenter.setCurrentPage(1);
                presenter.loadMedicalData();
                timeOrderDialo.hide();
            }
        });
    }

    @Override
    protected void initData() {
        //onRefresh(); //下拉框已经进行了 初始化
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        //TODO yuyang 请求数据
        presenter.resetAllConditionAndPage();
        presenter.loadMedicalData();
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
        if(this.isVisible()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setCanLoadMore(int state) {
        adapter.setLoadState(state);
    }

    @Override
    public void setListData(List<ZhiShiBean> result) {
        adapter.setDataBeans(result);
    }

    @Override
    public void addListData(List<ZhiShiBean> result) {
        adapter.addDataBeans(result);
    }
    @Override
    public void setQueryReturn() {
        synchronized(this) {
            mRefeshCount--;
        }
    }
}
