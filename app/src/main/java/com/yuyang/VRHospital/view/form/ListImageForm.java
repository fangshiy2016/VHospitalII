package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.utils.XmlParseUtil;
import com.yuyang.VRHospital.view.adapter.GridAdapter;
import com.yuyang.VRHospital.view.adapter.ListAdapter;

/**
 * Created by fanshy on 2016/6/26.
 */
public class ListImageForm extends BaseForm {

    private ListView    listView;
    private ListAdapter mListAdapter;
    private Context     mContext;

    public ListImageForm(Context context, ViewGroup parent, String tag, String zhishiCode,int layout_id) {
        super(context, parent, tag, zhishiCode, layout_id);
        mContext = context;
    }

    @Override
    protected View createView(String content) {
        String listValue = XmlParseUtil.ParseContent(content);
        String[] array = listValue.split(",");
        //改为解析XML

        listView = (ListView) inflater.inflate(R.layout.layout_form_list, null);
        listView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);

        mListAdapter = new ListAdapter(mContext, array);
        listView.setAdapter(mListAdapter);
        //listView.setMultiChoiceModeListener();

        return listView;
    }

    @Override
    public String getContent() {
        if(listView == null){
            return "";
        }
        return "";
    }

    @Override
    public boolean checkPostInfo() {
        return false;
    }
}
