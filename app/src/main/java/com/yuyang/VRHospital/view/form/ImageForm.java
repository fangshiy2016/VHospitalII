package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.utils.XmlParseUtil;
import com.yuyang.VRHospital.view.NoScroolGridView;
import com.yuyang.VRHospital.view.activity.DiagnoseExplainActivity;
import com.yuyang.VRHospital.view.activity.HistoryActivity;
import com.yuyang.VRHospital.view.adapter.GridAdapter;
import java.util.Vector;


/**
 * Created by yuyang on 16/4/23.
 */
public  class ImageForm {
    public Context context ;
    public ViewGroup parent;
    private String tag;
    private String mZshiCode;
    private String mLableName;
    public LayoutInflater inflater;

    public View itemView;
    private TextView mLable;
    private ImageView mMore;
    private ImageView mQuestion;
    private NoScroolGridView mGridView;
    private GridAdapter mGridAdapter;
    private String  mOpContent;


    public ImageForm(Context context, ViewGroup parent, String tag, String zhishiCode, int layout_id) {
        this.context = context;
        this.parent = parent;
        this.inflater = LayoutInflater.from(context);
        this.tag = tag;
        this.mZshiCode = zhishiCode;
        itemView = inflater.inflate(layout_id, null);
        mLable = (TextView) itemView.findViewById(R.id.diagnose_item_lable);
        mGridView = (NoScroolGridView) itemView.findViewById(R.id.gridview_image);
        mMore = (ImageView) itemView.findViewById(R.id.diagnose_item_more);
        mQuestion = (ImageView) itemView.findViewById(R.id.diagnose_item_question);


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                mGridAdapter.changeState(position);
            }
        });
    }


    /**
     * 添加至布局
     */
    public void addToParent(String lableName,String content,boolean isShowMore,boolean isShowQuestion){
        mLable.setText(lableName);
        mLableName = lableName;
        mOpContent = content;
        //contentView = createView(content);
        String listValue = XmlParseUtil.ParseContent(content);
        String[] array = listValue.split(",");
        mGridAdapter = new GridAdapter(context, array);
        mGridView.setAdapter(mGridAdapter);

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
                    b.putString(DiagnoseExplainActivity.LABLE, tag);
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void setItemContentNoBg(){
        mGridView.setBackgroundResource(R.drawable.item_layout_bg_no_line);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    //protected abstract View createView(String content);

    public String getLable() {
        if(mLableName == null){
            return "";
        }
        return mLableName;//checkedRadio.getText().toString();
    }
    /**
     * 获取该item内容
     */
    //public abstract String getContent();
    public String getContent() {
        if(mGridView == null){
            return "";
        }
        return mOpContent;//checkedRadio.getText().toString();
    }
    /**
     * 检查输入项
     */
    public boolean checkPostInfo(){
        return true;
    }

    public int getValue() {
        return mGridAdapter.GetSelectItem()+1;
    }
}
