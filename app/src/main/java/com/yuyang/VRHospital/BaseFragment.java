package com.yuyang.VRHospital;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by yuyang on 16/4/11.
 */
public abstract class BaseFragment extends Fragment {
    /** 标志位，标志View已经初始化完成 */
    protected boolean isPrepared = false;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    protected boolean mHasLoadedOnce = false;

    protected Toolbar toolbar;
    private ProgressDialog progressDialog;

    private View viewRoot;
    protected int layoutId = R.layout.activity_base;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initLayoutId();
        if(viewRoot == null) {
            viewRoot = inflater.inflate(layoutId, null);
            ButterKnife.bind(this, viewRoot);
            initAppBar();
            initViews();
        }
        ButterKnife.bind(this, viewRoot);
        isPrepared = true;
        return viewRoot;
    }

    private void initAppBar() {
        toolbar = (Toolbar) viewRoot.findViewById(R.id.toolbar);
        if (null != toolbar) {
            toolbar.setTitle(R.string.app_name);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getUserVisibleHint()){
            lazyLoad();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 重写改方法实现fragment懒加载
     * 该方法用于告诉系统，这个Fragment的UI是否是可见的
     * setUserVisibleHint是在onCreateView之前调用的
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint() && isPrepared){//如果该fragment可见,并且已经完成页面加载
            lazyLoad();
        }
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    private void lazyLoad(){
        if(mHasLoadedOnce){
            return;
        }
        initData();
        mHasLoadedOnce = true;
    }

    public void showProgressDialog(){
        progressDialog = ProgressDialog.show(getActivity(),null,"加载中，请稍后...",false);
    }

    public void hideProgressDialog(){
        if(progressDialog != null) {
            progressDialog.hide();
        }
    }

    /**
     * 设置基础布局
     */
    protected abstract void initLayoutId();

    /**
     * 初始化Views
     */
    protected abstract void initViews();

    /**
     * 加载数据
     */
    protected abstract void initData();
}
