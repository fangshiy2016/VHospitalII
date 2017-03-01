package com.yuyang.VRHospital.view.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyang.VRHospital.BaseFragment;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.CheckLogicBean;
import com.yuyang.VRHospital.bean.DiagnoseItemBean;
import com.yuyang.VRHospital.bean.LoginBean;
import com.yuyang.VRHospital.bean.LoginInfo;
import com.yuyang.VRHospital.bean.zhenDuanTemplate;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;
import com.yuyang.VRHospital.presenter.CheckLogicPresenterImpl;
import com.yuyang.VRHospital.presenter.DiagnosePresenterImpl;
import com.yuyang.VRHospital.presenter.iPresenter.IDiagnosePresenter;
import com.yuyang.VRHospital.utils.CropImage;
import com.yuyang.VRHospital.view.activity.DiagnoseResultActivity;
import com.yuyang.VRHospital.view.adapter.DialogAdapter;
import com.yuyang.VRHospital.view.form.DynamicFormHelper;
import com.yuyang.VRHospital.view.fragment.iFragment.IDiagnoseFragment;
import com.yuyang.VRHospital.view.widget.ListDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by yuyang on 16/4/19.
 * 辅助诊断
 */
public class DiagnoseFragment extends BaseFragment implements IDiagnoseFragment {

    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.diagnose_zhenduan_list)
    AppCompatSpinner zhenduanList;

    @Bind(R.id.diagnose_item_layout)
    LinearLayout itemLayout;
    @Bind(R.id.diagnose_submit)
    Button submit;
    @Bind(R.id.quick_diagnose_temp)
    ImageView mQuickDiagnoseTmp;

    private ListDialog stepDialog;

    private DynamicFormHelper formHelper;
    private IDiagnosePresenter diagnosePresenter;
    private IDiagnosePresenter checkLogicPresenter;
    private String  mSkipCode = "";
    private List<zhenDuanTemplate> mStepList;
    private ArrayAdapter<String> zhenduanAdapter;
    private CharSequence mQuickTemp[] = {"快速诊断模板1", "快速诊断模板2",  "快速诊断模板3"};

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_diagnose;
    }

    @Override
    protected void initViews() {
        title.setText(R.string.diagnose_title);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionCode = SPDao.getSharedPreferences(SPKey.QUESTION_CODE, "ZD_HPV_0001");
                Map<String, String> params = getSubmitContent();
                if (params != null){
                    checkLogicPresenter.submitDiagnoseContent(questionCode, params);
                }
            }
        });

        formHelper = new DynamicFormHelper(getActivity(), itemLayout, R.layout.layout_diagnose_item);
        diagnosePresenter   = new DiagnosePresenterImpl(this);
        checkLogicPresenter = new CheckLogicPresenterImpl(this);

        LoginInfo logininfo = SPDao.getObject(SPKey.ZHENDUN_TEMPLATES, LoginInfo.class);
        if(logininfo == null) return ;
        mStepList = logininfo.getZhenDuanTemplates();
        if(mStepList == null) return ;

        stepDialog = new ListDialog(getActivity(), "诊断模板");

        List<String> classStrs = new ArrayList<String>();// = Arrays.asList(getResources().getStringArray(R.array.medical_class));
        for(zhenDuanTemplate step:mStepList){
            classStrs.add(step.getTitle());
        }
        zhenduanAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item/*R.layout.layout_form_spinner_item*/, classStrs);
        zhenduanList.setAdapter(zhenduanAdapter);

        zhenduanList.setOnItemSelectedListener(new AppCompatSpinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                String questionCode = mStepList.get(arg2).getCode();
                SPDao.saveSharedPreferences(SPKey.QUESTION_CODE, questionCode);
                diagnosePresenter.getDiagnoseContent(questionCode);
               arg0.setVisibility(View.VISIBLE);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                arg0.setVisibility(View.VISIBLE);
            }
        });

        mQuickDiagnoseTmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialog);
                builder.setTitle("快速诊断模板选择");
                //builder.setIcon(R);

                builder.setItems(mQuickTemp, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                break;

                            case 1:

                                break;
                            case 2:

                                break;
                            default:
                                break;
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dlg = builder.create();
                Window dialogWindow = dlg.getWindow();
                /*实例化Window*/
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                /*实例化Window操作者*/
                lp.width = 80;
                lp.x = mQuickDiagnoseTmp.getRight(); // 新位置X坐标
                lp.y = -350;//mQuickDiagnoseTmp.getBottom(); // 新位置Y坐标
                dialogWindow.setAttributes(lp);
                dlg.show();

            }
        });
    }

    @Override
    protected void initData() {
        //TODO yuyang 请自行定义请求参数

    }

    @Override
    public void setDiagnoseContent(List<DiagnoseItemBean> itemBeans) {
        formHelper.parseDiagnoseShow(itemBeans);
    }

    @Override
    public void setDiagnoseCheckLogic(CheckLogicBean.CheckLogicInfo loginInfo){

        if(loginInfo.getSkipType() == 1){
            mSkipCode = loginInfo.getSkipCode();
            Toast.makeText(getActivity(), "正在跳转获取下一组问题", Toast.LENGTH_LONG).show();

        }else if(loginInfo.getSkipType() == 0){
            Intent intent = new Intent(getActivity(), DiagnoseResultActivity.class);
            Bundle b = new Bundle();

            b.putString(DiagnoseResultActivity.TAG, "诊断结果");
            b.putString(DiagnoseResultActivity.ANSWER_CODE, loginInfo.getSkipCode());

            intent.putExtras(b);
            startActivity(intent);
        }
        else {
            Toast.makeText(getActivity(), "未找到对应逻辑，请检查后台诊断逻辑设置!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public Map<String, String> getSubmitContent() {
        String checkInfo = formHelper.CheckBaseForm();
        if(checkInfo.length() > 0){
            Toast.makeText(getActivity(), checkInfo, Toast.LENGTH_LONG).show();
            return null;
        }
        Map<String,String> params = formHelper.getSubmitParams();
        String zhenDuanInfo =  formHelper.getSubmitContent();
        SPDao.saveSharedPreferences(SPKey.ZHENDUN_INFO,  zhenDuanInfo);

        return params;
    }

    @Override
    public void getNextQuestion(){
        if(!mSkipCode.isEmpty() && mSkipCode != "") {
            SPDao.saveSharedPreferences(SPKey.QUESTION_CODE, mSkipCode);
            diagnosePresenter.getDiagnoseContent(mSkipCode);
        }
    }

    @Override
    public void showErrorDialog() {
        Toast.makeText(getActivity(), R.string.load_fail, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadProgress() { showProgressDialog(); }

    @Override
    public void hideLoadProgress() {
        hideProgressDialog();
    }
}
