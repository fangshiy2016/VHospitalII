package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.view.activity.DiagnoseExplainActivity;
import com.yuyang.VRHospital.view.activity.HistoryActivity;

/**
 * Created by yuyang on 16/4/23.
 */
public abstract class BaseForm {
    public Context context ;
    public ViewGroup parent;

    private String mTitle;
    private String tag;
    private String mZshiCode;
    private int value;
    private int qType;

    public LayoutInflater inflater;

    public View contentView;
    public View itemView;
    private TextView mLable;
    private ImageView mMore;
    private ImageView mQuestion;
    private FrameLayout mItemContent;
    public  ImageView mUnselect;
    public BaseForm(Context context,ViewGroup parent,String tag, String zhishiCode, int layout_id) {
        this.context = context;
        this.parent = parent;
        this.inflater = LayoutInflater.from(context);
        this.tag = tag;
        this.mZshiCode = zhishiCode;
        mTitle = "";
        itemView = inflater.inflate(layout_id, null);
        mLable = (TextView) itemView.findViewById(R.id.diagnose_item_lable);
        mItemContent = (FrameLayout) itemView.findViewById(R.id.diagnose_item_content);
        mMore = (ImageView) itemView.findViewById(R.id.diagnose_item_more);
        mQuestion = (ImageView) itemView.findViewById(R.id.diagnose_item_question);
        mUnselect = (ImageView) itemView.findViewById(R.id.diagnose_item_unselect);
        mUnselect.setVisibility(View.INVISIBLE);
    }

    /**
     * 添加至布局
     */
    public void addToParent(int qType, String lableName,String content,boolean isShowMore,boolean isShowQuestion){
        mLable.setText(lableName);
        mTitle = lableName;
        this.qType = qType;
        contentView = createView(content);
        mItemContent.addView(contentView);
        parent.addView(itemView);
        if(mMore != null) {
            mMore.setVisibility(isShowMore ? View.VISIBLE : View.INVISIBLE);
            mQuestion.setVisibility(isShowQuestion ? View.VISIBLE : View.INVISIBLE);
            setListener(tag);
        }
    }

    private void setListener(final String title){
        if(mMore.getVisibility() == View.VISIBLE) {
            mMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HistoryActivity.class);
                    Bundle b = new Bundle();
                    b.putString(HistoryActivity.TAG, tag);
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }
        if(mQuestion.getVisibility() == View.VISIBLE) {
            mQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DiagnoseExplainActivity.class);
                    Bundle b = new Bundle();
                    b.putString(DiagnoseExplainActivity.TITLE, title);
                    b.putString(DiagnoseExplainActivity.LABLE, mZshiCode);
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void setItemContentNoBg(){
        mItemContent.setBackgroundResource(R.drawable.item_layout_bg_no_line);
    }

    public String getLable() {
        return mTitle;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    protected abstract View createView(String content);

    /**
     * 获取该item内容
     */
    public abstract String getContent();

    /**
     * 检查输入项
     */
    public abstract boolean checkPostInfo();

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getqType() {
        return qType;
    }

    public void setqType(int qType) {
        this.qType = qType;
    }
}
